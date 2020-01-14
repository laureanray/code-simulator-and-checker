package com.laureanray.codesimulatorandchecker.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.laureanray.codesimulatorandchecker.data.dao.StudentDao;
import com.laureanray.codesimulatorandchecker.data.databases.StudentDatabase;
import com.laureanray.codesimulatorandchecker.data.model.Student;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;

    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        allStudents = studentDao.getAllStudents();
    }

    public void insert(Student student){
        new InsertNoteAsyncTask(studentDao).execute(student);
    }

    public void update(Student student){
        new UpdateNoteAsyncTask(studentDao).execute(student);
    }

    public LiveData<List<Student>> getAllStudents(){
        return allStudents;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private InsertNoteAsyncTask(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyncTask extends AsyncTask<Student, Void, Void> {
        private StudentDao studentDao;

        private UpdateNoteAsyncTask(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.update(students[0]);
            return null;
        }
    }
}
