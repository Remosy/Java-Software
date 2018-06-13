public class Exit extends SimpleButton {
    public Exit(){
        super();
        this.setText("Exit");
    }

    @Override
    protected int changeCurrent() {
        return -1;
    }

}
