package co.edu.uniandes.csw.incidentes.entities;
import co.edu.uniandes.csw.incidentes.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author df.foreroc
 */
@Entity
public class IncidenteEntity extends BaseEntity implements Serializable {

    // Atributo que determina la relacion de Coordinador-Incidente
    @PodamExclude
    @ManyToOne
    private CoordinadorEntity coordinador;
    // Atributo que determina la relacion de Tecnico-Incidente
    @PodamExclude
    @ManyToOne
    private TecnicoEntity tecnico;
    // Atributo que determina la relacion de Empleado-Incidente
    @PodamExclude
    @ManyToOne
    private EmpleadoEntity empleado;
    //Fecha inicial del Incidente
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaHoraInicio;
    //Fecha final del Incidente
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaHoraFinal;
    //Descripcion del incidente
    private String descripcion;
    //Observaciones que se hacen al final
    private String observaciones;
    //Calificación que se da al final
    private Integer calificacion;
    //Categoria del incidente
    private String categoria;
    //Prioridad del incidente
    private String prioridad;
    //Si el incidente esta solucionado
    private Boolean solucionado;
    //Si el incidente se ha reabierto
    private Boolean reabrir;
    //Equipo donde se presenta el incidente
    private String equipo;

    // Atributo que determina la relacion de Incidente-Actuacion
    @PodamExclude
    @OneToMany(mappedBy = "incidente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ActuacionEntity> actuaciones = new ArrayList<>();

    
    
    //Método que devuelve todas las actuaciones de un incidente
    public List<ActuacionEntity> getActuaciones() {
        return actuaciones;
    }

    //Método que asigna las actuaciones a un incidente
    public void setActuaciones(List<ActuacionEntity> actu) {
        this.actuaciones = actu;
    }

    //Método que añade una actuacion a la lista de actuaciones
    public void addActuacion(ActuacionEntity actu) {
        this.actuaciones.add(actu);
    }

    //Método que da el coordinador
    public CoordinadorEntity getCoordinador() {
        return coordinador;
    }

    //Método que determina el coordinador
    public void setCoordinador(CoordinadorEntity coordinador) {
        this.coordinador = coordinador;
    }

    //Método que da el Tecnico del incidente
    public TecnicoEntity getTecnico() {
        return tecnico;
    }
    //Método que asigna el tecnico al incidente  

    public void setTecnico(TecnicoEntity tec) {
        this.tecnico = tec;
    }

    /**
     * @return the empleado
     */
    public EmpleadoEntity getEmpleado() {
        return empleado;
    }

    /**
     * @param empleado the empleado to set
     */
    public void setEmpleado(EmpleadoEntity empleado) {
        this.empleado = empleado;
    }

    /**
     * @return the fechaHoraInicio
     */
    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    /**
     * @param fechaHoraInicio the fechaHoraInicio to set
     */
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    /**
     * @return the fechaHoraFinal
     */
    public Date getFechaHoraFinal() {
        return fechaHoraFinal;
    }

    /**
     * @param fechaHoraFinal the fechaHoraFinal to set
     */
    public void setFechaHoraFinal(Date fechaHoraFinal) {
        this.fechaHoraFinal = fechaHoraFinal;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the prioridad
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the solucionado
     */
    public Boolean getSolucionado() {
        return solucionado;
    }

    /**
     * @param solucionado the solucionado to set
     */
    public void setSolucionado(Boolean solucionado) {
        this.solucionado = solucionado;
    }

    /**
     * @return the reabrir
     */
    public Boolean getReabrir() {
        return reabrir;
    }

    /**
     * @param reabrir the reabrir to set
     */
    public void setReabrir(Boolean reabrir) {
        this.reabrir = reabrir;
    }

    /**
     * @return the equipo
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
