/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Nilai;

/**
 *
 * @author Stickerman
 */
public class NilaiDao {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getBySearchStatement;

    private final String insertQuery = "insert into nilai(nim,kode_mk,nilai) "
            + " values(?,?)";
    private final String updateQuery = "update nilai set nim=?,kode_mk=? "
            + " nilai=? where nim=?";
    private final String deleteQuery = "delete from nilai where nim=?";
    private final String getByIdQuery = "select * from nilai where nim =?";
    private final String getAllQuery = "select * from nilai";
    private final String getBySearchQuery = "select * from nilai WHERE nim LIKE ?";

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
        getBySearchStatement = this.connection.prepareStatement(getBySearchQuery);
    }
    
    public Nilai save(Nilai nilai) throws SQLException{
        if (nilai.getNim() == 0) {
            insertStatement.setInt(1, nilai.getNim());
            insertStatement.setString(2, nilai.getKode_mk());
            insertStatement.setInt(3, nilai.getNilai());
            int id = (int) insertStatement.executeUpdate();
            nilai.setNim(id);
        } else {
            updateStatement.setInt(1, nilai.getNim());
            updateStatement.setSting(2, nilai.getKode_mk());
            updateStatement.setInt(3, nilai.getNilai());
            updateStatement.executeUpdate();
        }
        return nilai;
    }
    
    public Nilai delete(Nilai nilai) throws SQLException {
        deleteStatement.setInt(1, nilai.getNim());
        deleteStatement.executeUpdate();
        return nilai;
    }
    
    public Nilai getById(int id) throws SQLException{
        getByIdStatement.setInt(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            Nilai nilai = new Nilai();
            nilai.setNim(rs.getInt("nim"));
            nilai.setKode_mk(rs.getString("kode_mk"));
            nilai.setNilai(rs.getInt("nilai"));
            return nilai;
        }
        return null;
    }
    
    public List<Nilai> getBySearch(String cari) throws SQLException{
        List<Nilai> nilaiR = new ArrayList<>();
        getBySearchStatement.setString(1, '%'+cari+'%');
        ResultSet rs = getBySearchStatement.executeQuery();
        while(rs.next()){
            Nilai nilai = new Nilai();
            nilai.setNim(rs.getInt("nim"));
            nilai.setKode_mk(rs.getKode_mk("kode_mk"));
            nilai.setNilai(rs.getInt("nilai"));
            nilaiR.add(nilai);
        }
        return nilaiR;
    }
    
    public List<Nilai> getAll() throws SQLException{
        List<Nilai> nilaiR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Nilai nilai = new Nilai();
            nilai.setNim(rs.getInt("nim"));
            nilai.setKode_mk(rs.getString("kode_mk"));
            nilai.setNilai(rs.getInt("nilai"));
            nilaiR.add(nilai);
        }
        return nilaiR;
    }
}
