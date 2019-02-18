package com.usdataproject.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.usdataproject.data.model.FailureAssistanceTransaction;
import com.usdataproject.data.model.FdicInstitutionDirectoryRecord;
import com.usdataproject.util.helper.StringHelper;

/**
 * create table if not exists fdic_institution_directory ( directory_item_id INT
 * NOT NULL AUTO_INCREMENT PRIMARY KEY title VARCHAR(50) NULL definition
 * VARCHAR(50) NOT NULL );
 */

public class FdicInstitutionDirectory {
	private final static int IDX_DIRECTORY_ITEM = 1;
	private final static int IDX_TITLE = 2;
	private final static int IDX_DEFINITION = 3;

	public final static void batchInsert(final List<FdicInstitutionDirectoryRecord> p_lstRecords) {
		final Connection connection = FdicDatabaseHelper.getConnectionAsFdicUser();
		try {
			final PreparedStatement stat = connection.prepareStatement(
					"insert into fdic.fdic_institution_directory (directory_item, title, definition) values(?,?,?)");
			for (FdicInstitutionDirectoryRecord directoryRecord : p_lstRecords) {
				stat.setString(IDX_DIRECTORY_ITEM,
						StringHelper.getTrimmedStringOrNull(directoryRecord.getDirectoryItem()));
				stat.setString(IDX_TITLE, StringHelper.getTrimmedStringOrNull(directoryRecord.getTitle()));
				stat.setString(IDX_DEFINITION, StringHelper.getTrimmedStringOrNull(directoryRecord.getDefinition()));
				stat.addBatch();
			}
			stat.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final static void batchFailureAssistanceTransactionsInsert(
			final List<FailureAssistanceTransaction> transactions) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		try (final Session session = factory.openSession()) {
			final Transaction transaction = session.beginTransaction();
			int i = 0;
			for (FailureAssistanceTransaction failureTransaction : transactions) {
				session.save(failureTransaction);

				if (i % 50 == 0) {
					session.flush();
					session.clear();
				}

				i++;
			}
			transaction.commit();
		}
	}

	public static void main(String[] args) {
		readAllFdicDirectoryRecords();
	}

	public static void readAllFdicDirectoryRecords() {

		try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {

			Transaction transaction = session.beginTransaction();

			EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			CriteriaQuery<FdicInstitutionDirectoryRecord> query = criteriaBuilder
					.createQuery(FdicInstitutionDirectoryRecord.class);
			query.select(query.from(FdicInstitutionDirectoryRecord.class));

			for (FdicInstitutionDirectoryRecord record : session.createQuery(query).getResultList()) {
				System.out.println("id: " + record.getId() + " directory item: " + record.getDirectoryItem());
			}

			transaction.commit();
		}
	}

	public static void getAllFdicDirectoryEntries() {
		try {
			Connection connection = FdicDatabaseHelper.getConnectionAsFdicUser();

			final ResultSet rs = connection.createStatement().executeQuery("select * from fdic_institution_directory");

			if (rs.next()) {
				System.out.println("found data");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
