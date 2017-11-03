package de.fhg.iais.roberta.javaServer.basics;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.fhg.iais.roberta.persistence.bo.UserGroup;
<<<<<<< ad122ec816f8024ef6384122bf46e051c4480432
=======
import de.fhg.iais.roberta.persistence.dao.GroupDao;
import de.fhg.iais.roberta.persistence.dao.UserDao;
>>>>>>> #384 copied old dashboard to current develop
import de.fhg.iais.roberta.persistence.dao.UserGroupDao;
import de.fhg.iais.roberta.persistence.util.DbSession;
import de.fhg.iais.roberta.persistence.util.DbSetup;
import de.fhg.iais.roberta.persistence.util.SessionFactoryWrapper;

public class UserGroupDaoTest {
    private SessionFactoryWrapper sessionFactoryWrapper;
    private DbSetup memoryDbSetup;
    private String connectionUrl;
    private Session nativeSession;
    private DbSession hSession;
<<<<<<< ad122ec816f8024ef6384122bf46e051c4480432
=======
    private UserDao userDao;
    private GroupDao groupDao;
>>>>>>> #384 copied old dashboard to current develop
    UserGroupDao userGroupDao;

    @Before
    public void setup() throws Exception {
        this.connectionUrl = "jdbc:hsqldb:mem:createNewGroupDb";
        this.sessionFactoryWrapper = new SessionFactoryWrapper("hibernate-test-cfg.xml", this.connectionUrl);
        this.nativeSession = this.sessionFactoryWrapper.getNativeSession();
        this.memoryDbSetup = new DbSetup(this.nativeSession);
        this.memoryDbSetup.runDefaultRobertaSetup();
        this.hSession = this.sessionFactoryWrapper.getSession();
<<<<<<< ad122ec816f8024ef6384122bf46e051c4480432
=======
        this.userDao = new UserDao(this.hSession);
        this.groupDao = new GroupDao(this.hSession);
>>>>>>> #384 copied old dashboard to current develop
        this.userGroupDao = new UserGroupDao(this.hSession);
    }

    @Test
    public void persistUserGroupNotNull() throws Exception {
        UserGroup userGroup = this.userGroupDao.persistUserGroup("Roberta", "TestGroup138");
        this.hSession.commit();
        Assert.assertNotNull(userGroup);
    }

    @Test
    public void persistUserGroupNull() throws Exception {
        UserGroup userGroup = this.userGroupDao.persistUserGroup("Roberta", "TestGroup");
        this.hSession.commit();
        Assert.assertNull(userGroup);
    }

    @Test
    public void deleteByIds() throws Exception {
        int deleted = this.userGroupDao.deleteByIds("Roberta", "TestGroup1");
        this.hSession.commit();
        Assert.assertTrue(deleted == 1);
    }

    @Test
    public void deleteByIdsNotDeleted() throws Exception {
        int deleted = this.userGroupDao.deleteByIds("Roberta", "TestGroup138");
        this.hSession.commit();
        Assert.assertTrue(deleted == 0);
    }

}
