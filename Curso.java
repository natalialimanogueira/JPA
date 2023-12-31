package entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    @Column(name = "nomecurso", nullable = false)
    private String nomecurso;

    @ManyToMany
    @JoinTable( name="aluno_curso",
            joinColumns = @JoinColumn(name="idcurso"),
            inverseJoinColumns = @JoinColumn(name="idaluno")
    )
    private List<Aluno> alunos = new ArrayList<>();

    public Curso() {

    }

    public Curso(Integer idcurso, String nomecurso) {
        this.idcurso = idcurso;
        this.nomecurso = nomecurso;
    }

    public void adicionarAluno(Aluno a) {
        this.alunos.add(a);
        a.getCursos().add(this);
    }

    public void removeAluno(Aluno a) {
        this.alunos.remove(a);
        a.getCursos().remove(this);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcurso, nomecurso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        return Objects.equals(idcurso, other.idcurso) && Objects.equals(nomecurso, other.nomecurso);
    }

    @Override
    public String toString() {
        return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
    }


}
