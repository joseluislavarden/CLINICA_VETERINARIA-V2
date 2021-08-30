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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */

public class AtencionGeneral {
    private int nroatencion;
    private String fechaatencion;
    private int total;
    private int idpersonal;
    private int ci_propietario;
    DefaultTableModel model;
    Conexion cnx=new Conexion();
 

    
    public AtencionGeneral(int nroatencio, String fechaaten, int total, int idperasis, int ci_pro) {
        this.nroatencion = nroatencio;
        this.fechaatencion = fechaaten;
        this.total = total;
        this.idpersonal= idperasis;
        this.ci_propietario=ci_pro;
   
      
    }
     public AtencionGeneral() {
        this.nroatencion = 0;
        this. fechaatencion = "";
        this.total = 0;
        this.idpersonal = 0;
        this.ci_propietario = 0;
 
        
    }

    public int getNroatencion() {
        return nroatencion;
    }

    public void setNroatencion(int nroatencion) {
        this.nroatencion = nroatencion;
    }

    public String getFechaatencion() {
        return fechaatencion;
    }

    public void setFechaatencion(String fechaatencion) {
        this.fechaatencion = fechaatencion;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(int idpersonal) {
        this.idpersonal = idpersonal;
    }

    public int getCi_propietario() {
        return ci_propietario;
    }

    public void setCi_propietario(int ci_propietario) {
        this.ci_propietario = ci_propietario;
    }

   
   

      
    public void buscartotal(int nraten){
     
      this.setNroatencion(nraten);
      cnx.EjecutarComando("select total from atencion_general where nro_atencion="+nroatencion+");");
     
   }  
    public void Insertarsintotal(int nraten,String atenfecha, int idvet, int idasis){
     
      this.setNroatencion(nraten);
      this.setFechaatencion(atenfecha);
      this.setIdpersonal(idvet);
      this.setCi_propietario(idasis);
     
      cnx.EjecutarComando("insert into atencion_general (nro_atencion,fecha,idpersonal,ci_propietario) values("+nroatencion+",'"+fechaatencion+"',"+idpersonal+","+ci_propietario+");");
     
   }  
    
 public void Eliminar(int nraten,String atenfecha,int totals, int idvet, int idasis, int nrohist)
   {
      this.setNroatencion(nraten);
      this.setFechaatencion(atenfecha);
      this.setTotal(totals);
    
      this.setIdpersonal(idasis);
     
       cnx.EjecutarComando("delete from detalleservicio where nroatencion="+nraten+";");
      cnx.EjecutarComando("delete from atenciongeneral where nroatencion="+nraten+";");
       
     
   }
 public void Actualizar(int nraten,String atenfecha,int totals, int idvet, int idasis, int nrohist)
    {
   
      this.setNroatencion(nraten);
      this.setFechaatencion(atenfecha);
      this.setTotal(totals);
      this.setIdpersonal(idasis);
  
  
      cnx.EjecutarComando("update atenciongeneral set nratencion='"+nraten +"',fechaantencion='"+atenfecha+"',total='"+totals+"',idpersonalvet='"+idvet+"',idpersonal='"+idasis+"',nrohistorial='"+nrohist+" where nroatencion='"+nraten+"';");
    }
  public void Actualizartotal(int nraten)
    {
   
      this.setNroatencion(nraten);

 
  
  
      cnx.EjecutarComando(" update atencion_general set total=(SELECT  SUM(preciounitario)  from detalle_servicio where nro_atencion="+nroatencion+")\n" +
"  where nro_atencion="+nroatencion+";");
    }
    public void Buscar (JTable aten,String Valor)
    {
        try {
         //   u.IdUsuario,c.CIPersonal,c.Nombre,c.Apellido,c.FechaNac,c.FechaIng,c.Celular,u.Contraseña
            String [] titulos = {"N° ATENCION","FECHA","TOTAL","ID PERSONAL","CARNET PROPIETARIO"};
            String[] Registros = new String[15];
            model = new DefaultTableModel(null, titulos);
            ResultSet rs=cnx.EjecutarConsulta("SELECT nro_atencion,fecha,total,idpersonal,ci_propietario FROM atencion_general ; ");
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
            aten.setModel(model);
          } catch (SQLException ex)
            {
              Logger.getLogger(AtencionGeneral.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


   
         
    public String Pci ()
    {
        return "SELECT max(perci) from persona";
    }
     public  String verCI()
    {
        return "select CI from cliente;";
    }
   
 
    
    
}
