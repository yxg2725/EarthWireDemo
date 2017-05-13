package com.huadin.earthwire.Model.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.huadin.earthwire.Model.dao.bean.Person;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON".
*/
public class PersonDao extends AbstractDao<Person, Long> {

    public static final String TABLENAME = "PERSON";

    /**
     * Properties of entity Person.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property ProjectTeam = new Property(2, String.class, "projectTeam", false, "PROJECT_TEAM");
        public final static Property ProjectTeamId = new Property(3, int.class, "projectTeamId", false, "PROJECT_TEAM_ID");
        public final static Property ProjectTeamHead = new Property(4, String.class, "projectTeamHead", false, "PROJECT_TEAM_HEAD");
        public final static Property ProjectTeamHeadPhone = new Property(5, String.class, "projectTeamHeadPhone", false, "PROJECT_TEAM_HEAD_PHONE");
    };


    public PersonDao(DaoConfig config) {
        super(config);
    }
    
    public PersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"PROJECT_TEAM\" TEXT," + // 2: projectTeam
                "\"PROJECT_TEAM_ID\" INTEGER NOT NULL ," + // 3: projectTeamId
                "\"PROJECT_TEAM_HEAD\" TEXT," + // 4: projectTeamHead
                "\"PROJECT_TEAM_HEAD_PHONE\" TEXT);"); // 5: projectTeamHeadPhone
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String projectTeam = entity.getProjectTeam();
        if (projectTeam != null) {
            stmt.bindString(3, projectTeam);
        }
        stmt.bindLong(4, entity.getProjectTeamId());
 
        String projectTeamHead = entity.getProjectTeamHead();
        if (projectTeamHead != null) {
            stmt.bindString(5, projectTeamHead);
        }
 
        String projectTeamHeadPhone = entity.getProjectTeamHeadPhone();
        if (projectTeamHeadPhone != null) {
            stmt.bindString(6, projectTeamHeadPhone);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String projectTeam = entity.getProjectTeam();
        if (projectTeam != null) {
            stmt.bindString(3, projectTeam);
        }
        stmt.bindLong(4, entity.getProjectTeamId());
 
        String projectTeamHead = entity.getProjectTeamHead();
        if (projectTeamHead != null) {
            stmt.bindString(5, projectTeamHead);
        }
 
        String projectTeamHeadPhone = entity.getProjectTeamHeadPhone();
        if (projectTeamHeadPhone != null) {
            stmt.bindString(6, projectTeamHeadPhone);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Person readEntity(Cursor cursor, int offset) {
        Person entity = new Person( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // projectTeam
            cursor.getInt(offset + 3), // projectTeamId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // projectTeamHead
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // projectTeamHeadPhone
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Person entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProjectTeam(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProjectTeamId(cursor.getInt(offset + 3));
        entity.setProjectTeamHead(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setProjectTeamHeadPhone(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Person entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Person entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
