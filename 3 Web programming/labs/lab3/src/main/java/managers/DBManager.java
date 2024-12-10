package managers;

import jakarta.enterprise.context.ApplicationScoped;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import models.Point;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class DBManager implements Serializable {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DBManager.class);
    private static SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(DBManager.class.getName());
    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Point.class).buildSessionFactory();
    }

    public void addPoint(Point point) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(point);
            transaction.commit();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public Point getPointById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Point.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return null;
        }
    }

    public List<Point> getAllPoints() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Point", Point.class).getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return null;
        }
    }

    public void clearTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Point").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}