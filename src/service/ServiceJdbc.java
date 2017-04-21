package service;


import dao.MahasiswaDao;
import dao.MatkulDao;
import dao.NilaiDao;
import model.Mahasiswa;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Matkul;
import model.Nilai;

/**
 *
 * @author StickerMan
 */
public class ServiceJdbc {

    private MahasiswaDao mahasiswaDao;
    private MatkulDao matkulDao;
    private NilaiDao nilaiDao;
    private Connection connection;
    
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            mahasiswaDao = new MahasiswaDao();
            matkulDao = new MatkulDao();
            nilaiDao = new NilaiDao();
            mahasiswaDao.setConnection(connection);
            matkulDao.setConnection(connection);
            nilaiDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Mahasiswa save(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    
    public Mahasiswa update(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.update(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa delete(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public Mahasiswa getMahasiswa(int nim) {
        try {
            return mahasiswaDao.getByNim(nim);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Matkul save(Matkul matkul) {
        try {
            connection.setAutoCommit(false);
            matkulDao.save(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public Matkul delete(Matkul matkul) {
        try {
            connection.setAutoCommit(false);
            matkulDao.delete(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public Matkul getMatkul(int id) {
        try {
            return matkulDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Matkul> getAllMatkul() {
        try {
            return matkulDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public Nilai save(Nilai matkul) {
        try {
            connection.setAutoCommit(false);
            nilaiDao.save(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public Nilai delete(Nilai matkul) {
        try {
            connection.setAutoCommit(false);
            nilaiDao.delete(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public Nilai getNilai(int id) {
        try {
            return nilaiDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Nilai> getAllNilai() {
        try {
            return nilaiDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
