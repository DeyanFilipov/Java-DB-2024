package softuni.exam.models.dto.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "astronomers")
@XmlAccessorType(XmlAccessType.FIELD)
public class AstronomerRootDTO implements Serializable {

    @XmlElement(name = "astronomer")
    private List<AstronomerSeedDTO> astronomerSeedDTOS;

    public List<AstronomerSeedDTO> getAstronomerSeedDTOS() {
        return astronomerSeedDTOS;
    }

    public void setAstronomerSeedDTOS(List<AstronomerSeedDTO> astronomerSeedDTOS) {
        this.astronomerSeedDTOS = astronomerSeedDTOS;
    }
}
