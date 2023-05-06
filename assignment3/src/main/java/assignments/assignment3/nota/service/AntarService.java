package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    // boolean cek doWork()
    private boolean pernahWork = false;
    
    @Override
    public String doWork() {
        // TODO
        pernahWork = true;
        return "Sedang mengantar...";
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
        // minimal 2000 berarti jika <4 kilo, jadi 4 kilo
        if(berat < 4){
            return 2000;
        } else {
            return berat * 500;
        }
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
