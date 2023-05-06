package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    // boolean cek doWork()
    private boolean pernahWork = false;

    @Override
    public String doWork() {
        // TODO
        pernahWork = true;
        return "Sedang mencuci...";
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
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
