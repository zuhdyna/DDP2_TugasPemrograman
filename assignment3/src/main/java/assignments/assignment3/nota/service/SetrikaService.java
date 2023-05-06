package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    // boolean cek doWork()
    private boolean pernahWork = false;
    
    @Override
    public String doWork() {
        // TODO
        pernahWork = true;
        return "Sedang menyetrika...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if(pernahWork){
            return true;
        }
        return false;
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        return berat*1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
