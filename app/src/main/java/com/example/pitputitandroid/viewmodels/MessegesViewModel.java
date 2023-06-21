package com.example.pitputitandroid.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pitputitandroid.entities.Messege;

import java.util.List;


public class MessegesViewModel extends ViewModel {

    private MessegesRepository mRepository;

    private LiveData<List<Messege>> messeges;

    public MessegesViewModel () {

        mRepository = new MessegesRepository();
        messeges = mRepository.getAll();
    }

    public LiveData<List<Messege>> get(){
        return messeges;
    }

    public void add(Messege messege){ mRepository.add(messege); }
    public void delete(Messege messege){ mRepository.delete(messege); }
    public void reload(){ mRepository.reload(); }



}
