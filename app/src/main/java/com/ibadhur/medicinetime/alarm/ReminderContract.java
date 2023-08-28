package com.ibadhur.medicinetime.alarm;

import com.ibadhur.medicinetime.BasePresenter;
import com.ibadhur.medicinetime.BaseView;
import com.ibadhur.medicinetime.data.source.History;
import com.ibadhur.medicinetime.data.source.MedicineAlarm;



public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
