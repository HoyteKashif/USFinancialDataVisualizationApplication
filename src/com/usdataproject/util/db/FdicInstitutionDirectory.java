package com.usdataproject.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
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
import com.usdataproject.util.helper.ValidationHelper;

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
			final PreparedStatement statement = connection.prepareStatement(
					"insert into fdic.fdic_institution_directory (directory_item, title, definition) values(?,?,?)");
			for (FdicInstitutionDirectoryRecord directoryRecord : p_lstRecords) {
				statement.setString(IDX_DIRECTORY_ITEM,
						ValidationHelper.getTrimmedStringOrNull(directoryRecord.getDirectoryItem()));
				statement.setString(IDX_TITLE, ValidationHelper.getTrimmedStringOrNull(directoryRecord.getTitle()));
				statement.setString(IDX_DEFINITION,
						ValidationHelper.getTrimmedStringOrNull(directoryRecord.getDefinition()));
				statement.addBatch();
			}
			statement.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public final static void batchFailureAssistanceTransactionsInsert(
			final List<FailureAssistanceTransaction> p_lstEntries) {
		Session session = null;
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		session = factory.openSession();

		try {
			final Transaction transaction = session.beginTransaction();
			int iCounter = 0;
			for (FailureAssistanceTransaction recordModel : p_lstEntries) {
				session.save(recordModel);

				if (iCounter % 50 == 0) {
					session.flush();
					session.clear();
				}

				iCounter++;
			}
			transaction.commit();
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	// InsertIntocreate table if not exists failure_assistance_transactions
	// (
	// transaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	// institution_name VARCHAR(100),
	// cert INT,
	// fin INT,
	// location VARCHAR(100),
	// effective_date DATE,
	// ins_fund VARCHAR(20),
	// transaction_type VARCHAR(20),
	// charter_class VARCHAR(20),
	// failure_or_assistance VARCHAR(20),
	// total_deposits VARCHAR(20),
	// total_assets VARCHAR(20),
	// estimated_loss_as_12_3_2016 VARCHAR(20)
	// )
	// ;

	public static void readAllFdicDirectoryRecords() {
		Session session = null;
		try {
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			Transaction transaction = session.beginTransaction();

			EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			CriteriaQuery<FdicInstitutionDirectoryRecord> query = criteriaBuilder
					.createQuery(FdicInstitutionDirectoryRecord.class);
			// setting the from clause
			query.select(query.from(FdicInstitutionDirectoryRecord.class));

			List<FdicInstitutionDirectoryRecord> lstRecords = session.createQuery(query).getResultList();
			for (Iterator<FdicInstitutionDirectoryRecord> iterator = lstRecords.iterator(); iterator.hasNext();) {
				FdicInstitutionDirectoryRecord record = (FdicInstitutionDirectoryRecord) iterator.next();
				System.out.println("id: " + record.getId() + " directory item: " + record.getDirectoryItem());
			}
			transaction.commit();
		} catch (Throwable e) {
			System.err.println("Failed to create sessionFactory object." + e);
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public static void getAllFdicDirectoryEntries() {
		try {
			Connection connection = FdicDatabaseHelper.getConnectionAsFdicUser();

			final ResultSet resultSet = connection.createStatement()
					.executeQuery("select * from fdic_institution_directory");

			if (resultSet.next()) {
				System.out.println("found data");
			}
			System.out.println("connection was good");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
