package managers;

import models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;


import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArraySet;


public class DBManager {
    public static final Logger logger = LoggerFactory.getLogger(DBManager.class);
    private final String url = "jdbc:postgresql://localhost:5432/studs";
    private String username;
    private String password;
    private Connection conn;

    public DBManager(String[] args) {
        try {
            var filename = args[0];
            if (filename != null | !filename.isEmpty()) {
                var fileReader = new Scanner(new File(filename));
                File file = new File(args[0]);
                var s = new StringBuilder("");
                String[] db = new String[2];
                byte counter = 0;
                for (int i = 0; i < db.length; i++) {
                    counter += 1;
                    db[i] = fileReader.nextLine();
                }
                if (counter != 2) {
                    logger.error("Неверное количество параметров. Сервер не запущен!");
                    System.exit(1);
                } else {
                    this.username = db[0];
                    this.password = db[1];
                }
            } else {
                logger.error("Файл не найден. Сервер не запущен!");
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            logger.error("Файл не найден. Сервер не запущен!");
            System.exit(1);
        }
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            logger.info("Подключение к базе данных прошло успешно!");
        } catch (SQLException e) {
            logger.error("При подключении к базе данных произошла ошибка. Сервер не запущен!");
            System.exit(1);
        }
    }

    public boolean insertGroup(StudyGroup studyGroup) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into admins(name,height,eyeColor,nationality) values(?,?,?,?) returning id");
            Person admin = studyGroup.getGroupAdmin();
            stmt.setString(1, admin.getName());
            stmt.setInt(2, Long.valueOf(admin.getHeight()).intValue());
            stmt.setString(3, admin.getEyeColor().toString());
            if (admin.getNationality() != null) stmt.setString(4, admin.getNationality().toString());
            else stmt.setNull(4, Types.NULL);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            stmt = conn.prepareStatement("insert into studyGroups(name,x,y,creationDate,studentsCount,formOfEducation,semester,owner,groupAdmin) values(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, studyGroup.getName());
            stmt.setInt(2, Long.valueOf(studyGroup.getCoordinates().getX()).intValue());
            stmt.setInt(3, Long.valueOf(studyGroup.getCoordinates().getY()).intValue());
            stmt.setDate(4, java.sql.Date.valueOf(studyGroup.getCreationDate()));
            stmt.setInt(5, Long.valueOf(studyGroup.getStudentsCount()).intValue());
            stmt.setString(6, studyGroup.getFormOfEducation().toString());
            stmt.setString(7, studyGroup.getSemester().toString());
            stmt.setString(8, studyGroup.getOwner().toString());
            stmt.setInt(9, rs.getInt("id"));
            int hui = stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("sfd");
            System.out.println(e.getMessage() + e.getErrorCode());
            return false;
        }

    }

    public long getFreeId() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT last_value FROM studygroups_id_seq;");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1) + 1;
            } else return -1;
        } catch (SQLException e) {
            return -1;
        }
    }

    synchronized public void loadCollection(CopyOnWriteArraySet<StudyGroup> collection) {
        try {
            collection.clear();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM studygroups JOIN admins ON studygroups.groupadmin = admins.id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long groupId = rs.getLong(1);
                String groupName = rs.getString(2);
                Long x = rs.getLong(3);
                Integer y = rs.getInt(4);
                LocalDate creationDate = rs.getDate(5).toLocalDate();
                int studentsCount = rs.getInt(6);
                FormOfEducation formOfEducation = FormOfEducation.valueOf(rs.getString(7));
                Semester semester = Semester.valueOf(rs.getString(8));
                String owner = rs.getString(9);
                String Adminname = rs.getString(12);
                long height = rs.getLong(13);
                Color eyeColor = Color.valueOf(rs.getString(14));
                String country = rs.getString(15);
                Country nationality;
                if (country != null) {
                    nationality = Country.valueOf(country);
                } else nationality = null;
                Person admin = new Person(Adminname, height, eyeColor, nationality);
                StudyGroup group=new StudyGroup(groupId, groupName, new Coordinates(x, y), creationDate, studentsCount, formOfEducation, semester, owner, admin);
                collection.add(group);
            }

        } catch (SQLException e) {
            collection = new CopyOnWriteArraySet<>();
        }
    }

    public StudyGroup getById(long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM studygroups JOIN admins ON studygroups.groupadmin = admins.id where studygroups.id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Long groupId = rs.getLong(1);
                String groupName = rs.getString(2);
                Long x = rs.getLong(3);
                Integer y = rs.getInt(4);
                LocalDate creationDate = rs.getDate(5).toLocalDate();
                int studentsCount = rs.getInt(6);
                FormOfEducation formOfEducation = FormOfEducation.valueOf(rs.getString(7));
                Semester semester = Semester.valueOf(rs.getString(8));
                String owner = rs.getString(9);
                String Adminname = rs.getString(12);
                long height = rs.getLong(13);
                Color eyeColor = Color.valueOf(rs.getString(14));
                String country = rs.getString(15);
                Country nationality;
                if (country != null) {
                    nationality = Country.valueOf(country);
                } else nationality = null;
                Person admin = new Person(Adminname, height, eyeColor, nationality);
                return new StudyGroup(groupId, groupName, new Coordinates(x, y), creationDate, studentsCount, formOfEducation, semester, owner, admin);
            } else return null;


        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteById(long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from studygroups where id = ? returning groupAdmin");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stmt = conn.prepareStatement("delete from admins where id = ?");
                stmt.setLong(1, rs.getLong(1));
                stmt.execute();
                return true;
            } else return false;

        } catch (SQLException e) {
            return false;
        }

    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            return null;
        }
    }

    public String getGroupsString() {
        try {
            StringBuilder info = new StringBuilder();
            ResultSet rs = executeQuery("SELECT * FROM studygroups order by name");
            while (rs.next()) {
                info.append(getById(rs.getLong("id")) + "\n\n");
            }
            return info.toString().trim();
        } catch (SQLException e) {
            return "";
        }
    }

    public CopyOnWriteArraySet<StudyGroup> getStudyGroups() {
        try {
            CopyOnWriteArraySet<StudyGroup> studyGroups = new CopyOnWriteArraySet<>();
            ResultSet rs = executeQuery("SELECT * FROM studygroups order by name");
            while (rs.next()) {
                studyGroups.add(getById(rs.getLong("id")));
            }
            return studyGroups;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean registerUser(String username, String password) {
        try {
            PreparedStatement st = conn.prepareStatement("insert into users(login,password) values(?,?)");
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean exists(String login, String password) {
        try {
            PreparedStatement st = conn.prepareStatement("select password from users WHERE login=?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(1))) return true;
                else return false;
            } else return false;
        } catch (SQLException e) {
            return false;
        }

    }


    public boolean update(Long id, StudyGroup newStudyGroup) {
        try {
            String query = "update studygroups set name=?, x=?, y=?,creationDate=?,studentscount=?, formofeducation=?,semester=? , owner=? where id=? returning groupAdmin";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, newStudyGroup.getName());
            st.setLong(2, newStudyGroup.getCoordinates().getX());
            st.setInt(3, newStudyGroup.getCoordinates().getY());
            st.setDate(4, java.sql.Date.valueOf(newStudyGroup.getCreationDate()));
            st.setInt(5, newStudyGroup.getStudentsCount());
            st.setString(6, newStudyGroup.getFormOfEducation().toString());
            st.setString(7, newStudyGroup.getSemester().toString());
            st.setString(8, newStudyGroup.getOwner());
            st.setLong(9, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                query = "update studygroups set name=?,height=?, eyecolor=?, nationality=? where id=?";
                st = conn.prepareStatement(query);
                st.setString(1, newStudyGroup.getGroupAdmin().getName());
                st.setLong(2, newStudyGroup.getGroupAdmin().getHeight());
                st.setString(3, newStudyGroup.getGroupAdmin().getEyeColor().toString());
                Country country = newStudyGroup.getGroupAdmin().getNationality();
                if (country != null) st.setString(4, newStudyGroup.getGroupAdmin().getNationality().toString());
                else st.setNull(4, Types.NULL);
                st.setLong(5, rs.getLong("groupAdmin"));
                return true;
            } else return false;
        } catch (SQLException e) {
            return false;
        }
    }


    public boolean clear(String login) {
        try {
            PreparedStatement stmt = conn.prepareStatement("delete from studygroups where owner = ?");
            stmt.setString(1, login);
            stmt.executeQuery();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
