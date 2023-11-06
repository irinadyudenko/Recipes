class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (! (obj instanceof ComplexNumber)) {
            return false;
        }
        else {
            ComplexNumber complexNumber = (ComplexNumber) obj;
            return complexNumber.re == this.re && complexNumber.im == this.im;
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (int) Double.doubleToLongBits(re);
        result = 31 * result + (int) Double.doubleToLongBits(im);
        return result;
    }
}