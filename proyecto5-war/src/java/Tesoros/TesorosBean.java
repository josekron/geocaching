/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tesoros;

import app.ejb.DescubrimientoFacade;
import app.ejb.TesoroFacade;
import app.entity.Descubrimiento;
import app.entity.Tesoro;
import app.entity.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author José Antonio Herrera Peña
 * @Moqup 9
 */
@Named(value = "tesorosBean")
@RequestScoped
public class TesorosBean implements Serializable {
    @EJB
    private DescubrimientoFacade descubrimientoFacade;
    
    @EJB
    private TesoroFacade tesoroFacade;
    
    

    public TesoroFacade getTesoroFacade() {
        return tesoroFacade;
    }

    public void setTesoroFacade(TesoroFacade tesoroFacade) {
        this.tesoroFacade = tesoroFacade;
    }
    
    public DescubrimientoFacade getDescubrimientoFacade() {
        return descubrimientoFacade;
    }

    public void setDescubrimientoFacade(DescubrimientoFacade descubrimientoFacade) {
        this.descubrimientoFacade = descubrimientoFacade;
    }

    /**
     * Creates a new instance of TesorosBean
     */

    
    List<Tesoro>listaTesoros;
    String nombreBusqueda;
    String zonaBusqueda;
    List<String> listaDificultad;
    String dificultadBusqueda;
    Usuario usuario;
    List<String> listaOpEncontrado;
    String opEcontrado;
    int longBusquedaX;
    int longBusquedaY;

    public int getLongBusquedaX() {
        return longBusquedaX;
    }

    public void setLongBusquedaX(int longBusquedaX) {
        this.longBusquedaX = longBusquedaX;
    }

    public int getLongBusquedaY() {
        return longBusquedaY;
    }

    public void setLongBusquedaY(int longBusquedaY) {
        this.longBusquedaY = longBusquedaY;
    }


    public String getOpEcontrado() {
        return opEcontrado;
    }

    public void setOpEcontrado(String opEcontrado) {
        this.opEcontrado = opEcontrado;
    }

    public List<String> getListaOpEncontrado() {
        return listaOpEncontrado;
    }

    public void setListaOpEncontrado(List<String> listaOpEncontrado) {
        this.listaOpEncontrado = listaOpEncontrado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDificultadBusqueda() {
        return dificultadBusqueda;
    }

    public void setDificultadBusqueda(String dificultadBusqueda) {
        this.dificultadBusqueda = dificultadBusqueda;
    }

    public List<String> getListaDificultad() {
        return listaDificultad;
    }

    public void setListaDificultad(List<String> listaDificultad) {
        this.listaDificultad = listaDificultad;
    }

    public List<Tesoro> getListaTesoros() {
        return listaTesoros;
    }

    public void setListaTesoros(List<Tesoro> listaTesoros) {
        this.listaTesoros = listaTesoros;
    }
    
    public String getZonaBusqueda() {
        return zonaBusqueda;
    }

    public void setZonaBusqueda(String zonaBusqueda) {
        this.zonaBusqueda = zonaBusqueda;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }
    
    
    public TesorosBean() {
        FacesContext context = FacesContext.getCurrentInstance(); 
        HttpSession sesion = (HttpSession) context.getExternalContext().getSession(true);
        usuario = (Usuario) sesion.getAttribute("user");
    }
    /**
     * @Autor:José Antonio Herrera Peña
     * @Description: método postConstruct para inicializar las variables.
     */
    @PostConstruct
    public void inicializarVariables(){
        listaDificultad = new ArrayList<String>();
        this.listaDificultad.add("Todos");
        for(int i=1;i<=5;i++)
            this.listaDificultad.add("Menor o igual a "+Integer.toString(i));
        listaOpEncontrado = new ArrayList<String>();
        listaOpEncontrado.add("Todos");
        listaOpEncontrado.add("Si");
        listaOpEncontrado.add("No");

        listaTesoros = this.tesoroFacade.findAll();
        if(listaTesoros==null)
            listaTesoros = new ArrayList<Tesoro>();
    }
    
    public List<Tesoro> devolverListaTesoros(){
        return listaTesoros;
        //return this.tesoroFacade.findAll();
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: convierte un int de dificultad en una cadena de texto tipo ***
     * @param dificultad
     * @return 
     */
    public String devolverDificultadString(int dificultad){
        String texto="";
        for(int i=1;i<=dificultad;i++){
            texto=texto+"*";
        }
        return texto;
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: recoge los datos del formulario de búsqueda y quita de 
     * listaTesoros los que no contengan dichos datos.
     */
    public void recogerDatosBusquedaTesoros(){
        listaTesoros = this.tesoroFacade.findAll();
        List<Tesoro>nuevalista = new ArrayList<Tesoro>();
        for(Tesoro t : listaTesoros){
            String nombreT="";
            String zonaT="";
            if(t.getNombre()!=null)
                nombreT = t.getNombre().toUpperCase();
            if(t.getZona()!=null)
                zonaT = t.getZona().toUpperCase();
            String encStr = this.devolverSiTesoroDescubierto(t.getIdTesoro());
            int difT = t.getDificultad();
            
            if((nombreBusqueda==null || nombreT.contains(this.nombreBusqueda.toUpperCase()))
                    &&(zonaBusqueda==null || zonaT.contains(this.zonaBusqueda.toUpperCase()))
                    &&(encStr.equalsIgnoreCase(this.opEcontrado) || this.opEcontrado.equalsIgnoreCase("Todos"))){
                if(this.dificultadBusqueda.equalsIgnoreCase("Todos"))
                    nuevalista.add(t);
                else if(this.dificultadBusqueda.equalsIgnoreCase("Menor o igual a 1") && difT==1)
                    nuevalista.add(t);
                else if(this.dificultadBusqueda.equalsIgnoreCase("Menor o igual a 2") && difT<=2)
                    nuevalista.add(t);
                else if(this.dificultadBusqueda.equalsIgnoreCase("Menor o igual a 3") && difT<=3)
                    nuevalista.add(t);
                else if(this.dificultadBusqueda.equalsIgnoreCase("Menor o igual a 4") && difT<=4)
                    nuevalista.add(t);
                else if(this.dificultadBusqueda.equalsIgnoreCase("Menor o igual a 5") && difT<=5)
                    nuevalista.add(t);
            }
        }
        //ordenar según la posición elegida:
        if(longBusquedaX>0 && longBusquedaY>0){
            Map<Double,Tesoro> mapa = new HashMap<Double,Tesoro>();
            for(Tesoro t : nuevalista){
                double d = this.distanciaeuclidea(longBusquedaX, longBusquedaX, 
                        Integer.parseInt(t.getLatitud()), Integer.parseInt(t.getLongitud()));
                mapa.put(d, t);
            }
            Iterator it = mapa.entrySet().iterator();
            nuevalista=new ArrayList<Tesoro>();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();
                nuevalista.add((Tesoro)e.getValue());
            }
            
        }
        listaTesoros = nuevalista;
        
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: resetea listaTesoros haciendo un findAll
     */
    public void resetearListaTesoros(){
        listaTesoros=this.tesoroFacade.findAll();
        if(listaTesoros==null)
            listaTesoros = new ArrayList<Tesoro>();
        nombreBusqueda="";
        zonaBusqueda="";
        longBusquedaX=0;
        longBusquedaY=0;
        this.dificultadBusqueda = "Todos";
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: devuelve si un tesoro ha sido descubierto alguna vez
     * @param idTesoro
     * @return 
     */
    public String devolverSiTesoroDescubierto(int idTesoro){
        List<Descubrimiento>lista = this.descubrimientoFacade.devolverDescubrimientosDeUnTesoro(idTesoro);
        if(lista==null || lista.size()<=0)
            return "No";
        else
            return "Si";
    }
    
    /**
     * @Autor: José Antonio Herrera Peña
     * @Description: devuelve una subcadena de texto con la longitud que se le pasa
     * @param st
     * @param longcadena
     * @return 
     */
    public String recortarCadenaString(String st, int longcadena){
        if(st.length()<=longcadena)
            return st;
        else{
            String texto = (String)st.subSequence(0, longcadena)+"...";
            return texto;
        }     
    }
    
    /**
     * @Autor: José Antonio Herrera
     * @Description: va al jsp que se indica y guarda el idTesoro en la sesión.
     * @return 
     */
    public String irAComentarioTesoro(int idTesoro){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().put("idTesoro", idTesoro);
        return "/jsf/tesoros/comentarioTesoro";
    }
    
     public String irAPrincipal(){
        return "/jsf/principal";
    }
     
    /**
     * @Autor: José Antonio Herrera Peña
     * @Description: calcula la distancia euclídea entre dos pares (latitud ,longitud)
     * @param la1
     * @param lo1
     * @param la2
     * @param lo2
     * @return 
     */ 
    public double distanciaeuclidea(int la1, int lo1, int la2, int lo2){
        /*D=SQR((X2-X1)^2+(Y2-Y1)^2)*/
        double res = Math.sqrt((la1-la2)*(la1-la2) + (lo1-lo2)*(lo1-lo2));
        return res;
    }
}
