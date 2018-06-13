public class Enter extends SimpleButton {

    public Enter(){
        super();
        this.setText("Enter");
    }

    @Override
    protected int changeCurrent() {
        return 1;
    }

}
