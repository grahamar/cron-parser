/**
 * 
 */
package net.redhogs.cronparser;


/**
 * @author grhodes
 * @since 10 Dec 2012 10:58:44
 */
public class Options {

    private boolean throwExceptionOnParseError;
    private CasingTypeEnum casingType;
    private boolean verbose;

    public Options() {
        this.throwExceptionOnParseError = true;
        this.casingType = CasingTypeEnum.Sentence;
        this.verbose = false;
    }

    /**
     * @return the throwExceptionOnParseError
     */
    public boolean isThrowExceptionOnParseError() {
        return throwExceptionOnParseError;
    }

    /**
     * @param throwExceptionOnParseError the throwExceptionOnParseError to set
     */
    public void setThrowExceptionOnParseError(boolean throwExceptionOnParseError) {
        this.throwExceptionOnParseError = throwExceptionOnParseError;
    }

    /**
     * @return the casingType
     */
    public CasingTypeEnum getCasingType() {
        return casingType;
    }

    /**
     * @param casingType the casingType to set
     */
    public void setCasingType(CasingTypeEnum casingType) {
        this.casingType = casingType;
    }

    /**
     * @return the verbose
     */
    public boolean isVerbose() {
        return verbose;
    }

    /**
     * @param verbose the verbose to set
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

}
