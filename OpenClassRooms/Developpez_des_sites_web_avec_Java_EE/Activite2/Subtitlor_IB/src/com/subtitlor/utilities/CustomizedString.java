package com.subtitlor.utilities;

import java.io.Serializable;

public class CustomizedString implements Serializable, Comparable {

    private static final long serialVersionUID      = 1L;
    private String            myString              = null;
    private boolean           translated            = false;
    private boolean           isTranslationFinished = false;

    public boolean isPasRemplie() {
        if ( this.myString != null && this.myString.startsWith( "lignePasRemplie" ) ) {
            return true;
        } else {
            return false;
        }
    }

    public CustomizedString( String record ) {
        super();
        this.myString = record;
    }

    public void setTranslated( boolean translated ) {
        this.translated = translated;
    }

    public boolean isTranslated() {
        return this.translated;
    }

    public CustomizedString() {
        super();
    }

    public String getMyString() {
        return myString;
    }

    public String myString() {
        return myString;
    }

    public void setMyString( String myString ) {
        this.myString = myString;
    }

    public Boolean isNumeric() {
        int entier;
        try {
            entier = Integer.parseInt( this.myString );
            return new Boolean( true );
        } catch ( NumberFormatException e ) {
            return new Boolean( false );
        }
    }

    public int getLongueurLignes() {
        return myString.length();
    }

    public boolean isTimeValues() {
        if ( this.myString.indexOf( "-->" ) != -1 ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStringTraduction() {
        if ( this.myString.indexOf( "-->" ) != -1 ) {            
            return false;
        } else {
            int entier;
            try {
                entier = Integer.parseInt( this.myString );
                return false;
            } catch ( NumberFormatException e ) {
                if ( !this.myString.equals( "" ) ) {
                    return true;
                } else if ( this.myString.startsWith( "lignePasRemplie" ) ) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public boolean isVide() {
        if ( this.myString.equals( "" ) ) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isnotTranslated() {
        if ( this.myString.equals( "" ) ) {
            return true;
        } else {
            if ( this.myString.startsWith( "lignePasRemplie" ) ) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isNotTranslated() {
        if ( this.myString.equals( "" ) ) {
            return true;
        } else {
            if ( this.myString.startsWith( "lignePasRemplie" ) ) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isTranslationFinished() {
        return isTranslationFinished;
    }

    public void setTranslationFinished( boolean isTranslationFinished ) {
        this.isTranslationFinished = isTranslationFinished;
    }

    @Override
    public int compareTo( Object arg0 ) {
        return 0;
    }

}
