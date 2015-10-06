
package ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for solveQuadratic complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="solveQuadratic">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="b" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="c" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solveQuadratic", propOrder = {
    "a",
    "b",
    "c"
})
public class SolveQuadratic {

    protected int a;
    protected int b;
    protected int c;

    /**
     * Gets the value of the a property.
     * 
     */
    public int getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     */
    public void setA(int value) {
        this.a = value;
    }

    /**
     * Gets the value of the b property.
     * 
     */
    public int getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     * 
     */
    public void setB(int value) {
        this.b = value;
    }

    /**
     * Gets the value of the c property.
     * 
     */
    public int getC() {
        return c;
    }

    /**
     * Sets the value of the c property.
     * 
     */
    public void setC(int value) {
        this.c = value;
    }

}
