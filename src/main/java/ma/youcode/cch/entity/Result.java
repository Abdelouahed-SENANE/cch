package ma.youcode.cch.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ma.youcode.cch.entity.embedded.ResultId;

@Entity
@Getter
@Setter
@Table(name = "results")
public class Result {

    @EmbeddedId
    private ResultId resultId;

    @ManyToOne
    private Cyclist cyclist;

    @ManyToOne
    private Stage stage;


}
