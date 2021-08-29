/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoclinica;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Male
 */
public class detalleservicio
{
    private int nro_atencion;
    private int codservicio;
    private int precio;
    DefaultTableModel model;
    Conexion cnx=new Conexion();
    
 public detalleservicio(int nro_atencion, int codservicio,int precio)
 {
     this.nro_atencion=nro_atencion;
     this.codservicio=codservicio;
     this.precio=precio;
     
 }  
 
public detalleservicio()
{
    this.nro_atencion=0;
 this.codservicio=0;
 this.precio=0;
} 

    public int getNro_atencion() {
        return nro_atencion;
    }

    public void setNro_atencion(int nro_atencion) {
        this.nro_atencion = nro_atencion;
    }

    public int getCodservicio() {
        return codservicio;
    }

    public void setCodservicio(int codservicio) {
        this.codservicio = codservicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }



public void Insertardetalleservicio(int nro_atencion,int codservicio,int precio){
     this.setNro_atencion(nro_atencion);
      this.setCodservicio(codservicio);
      this.setPrecio(precio);
      
      cnx.EjecutarComando("insert into detalle_servicio values("+nro_atencion+","+codservicio+","+precio+");");
     
   }
 public void Eliminar(int nraten,int cod)
   {
      this.setNro_atencion(nraten);
      this.setCodservicio(cod);
 
     
       cnx.EjecutarComando("delete from detalle_servicio where nro_atencion="+nro_atencion+" and cod_servicio="+codservicio+";");
     
       
     
   }
     
   
  public void Actualizar(int codservicio,String descripcion,int precio)
    {
   
      this.setCodservicio(codservicio);
     
      this.setPrecio(precio);
      cnx.EjecutarComando("update servicio set cod_servicio="+codservicio +",descripcion='"+descripcion+"',precio="+precio+" WHERE COD_SERVICIO="+codservicio+";");
      
    }
    public void BuscarIDser (JTable servicio,String Valor)
    {
        try {
         //   u.IdUsuario,c.CIPersonal,c.Nombre,c.Apellido,c.FechaNac,c.FechaIng,c.Celular,u.Contraseña
            String [] titulos = {"CODIGO SERVICIO","DESCRIPCION","PRECIO"};
            String[] Registros = new String[15];
            model = new DefaultTableModel(null, titulos);
            ResultSet rs;
           rs = cnx.EjecutarConsulta("SELECT cod_servicio,descripcion,precio FROM servicio WHERE cod_servicio="+Valor+"; ");
            ResultSetMetaData datos = rs.getMetaData();
            int nc=datos.getColumnCount();
            int i=0;
            int e=0;
            while (rs.next()) {
                Object f []= new Object [nc];
                for(i=0;i<nc;i++){
                    f[i]=rs.getObject(i+1);          
                }
                model.addRow(f);
            }
            servicio.setModel(model);
          } catch (SQLException ex)
            {
              Logger.getLogger(servicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     public void Buscarnro (JTable detalleservicio,String Valor)
    {
        try {
         //   u.IdUsuario,c.CIPersonal,c.Nombre,c.Apellido,c.FechaNac,c.FechaIng,c.Celular,u.Contraseña
            String [] titulos = {"NUMERO ATENCION","COD","SERVICIO","PRECIO"};
            String[] Registros = new String[15];
            model = new DefaultTableModel(null, titulos);
            ResultSet rs;
           rs = cnx.EjecutarConsulta("SELECT a.nro_atencion,s.cod_servicio,s.descripcion,d.preciounitario FROM detalle_servicio d, servicio s, atencion_general a where (d.nro_atencion=a.nro_atencion and  s.cod_servicio=d.cod_servicio)and a.nro_atencion="+Valor+"");
            ResultSetMetaData datos = rs.getMetaData();
            int nc=datos.getColumnCount();
            int i=0;
            int e=0;
            while (rs.next()) {
                Object f []= new Object [nc];
                for(i=0;i<nc;i++){
                    f[i]=rs.getObject(i+1);          
                }
                model.addRow(f);
            }
            detalleservicio.setModel(model);
          } catch (SQLException ex)
            {
              Logger.getLogger(servicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void Buscar (JTable detalleservicio,String Valor)
    {
        try {
         //   u.IdUsuario,c.CIPersonal,c.Nombre,c.Apellido,c.FechaNac,c.FechaIng,c.Celular,u.Contraseña
            String [] titulos = {"NUMERO ATENCION","SERVICIO","PRECIO"};
            String[] Registros = new String[15];
            model = new DefaultTableModel(null, titulos);
            ResultSet rs;
           rs = cnx.EjecutarConsulta("SELECT a.nro_atencion,s.descripcion,d.preciounitario FROM detalle_servicio d, servicio s, atencion_general a where d.nro_atencion=a.nro_atencion and  s.cod_servicio=d.cod_servicio");
            ResultSetMetaData datos = rs.getMetaData();
            int nc=datos.getColumnCount();
            int i=0;
            int e=0;
            while (rs.next()) {
                Object f []= new Object [nc];
                for(i=0;i<nc;i++){
                    f[i]=rs.getObject(i+1);          
                }
                model.addRow(f);
            }
            detalleservicio.setModel(model);
          } catch (SQLException ex)
            {
              Logger.getLogger(servicio.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      public void ListarServicio(JComboBox<servicio> comboservicio){
        try{
            ResultSet rs = cnx.EjecutarConsulta("select* from servicio ;");
            comboservicio.addItem(new servicio(0, "titulo",0));
            while(rs.next()){
                comboservicio.addItem(new servicio(
                        Integer.parseInt(rs.getString("cod_servicio")),
                        rs.getString("descripcion"), 
                        Integer.parseInt(rs.getString("precio"))
                )
                );
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al mostrar servicio \n"+e);
        }
    }   
}