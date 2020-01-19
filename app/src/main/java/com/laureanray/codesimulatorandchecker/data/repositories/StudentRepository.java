package com.laureanray.codesimulatorandchecker.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.laureanray.codesimulatorandchecker.data.dao.StudentDao;
import com.laureanray.codesimulatorandchecker.data.databases.StudentDatabase;
import com.laureanray.codesimulatorandchecker.data.model.Student;
import com.laureanray.codesimulatorandchecker.data.services.StudentService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    @Inject
    StudentService studentService;
    private Retrofit retrofit;

    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        allStudents = studentDao.getAllStudents();
        studentService = retrofit.create(StudentService.class);
    }






//    public void insert(Student student){
//        new InsertNoteAsyncTask(studentDao).execute(student);
//    }
//
//    public void update(Student student){
//        new UpdateNoteAsyncTask(studentDao).execute(student);
//    }
//
//    public void findStudent(String name) { new FindStudentTask(studentService).execute() }
//
//    public LiveData<List<Student>> getAllStudents(){
//        return allStudents;
//    }
//



//    private static class InsertNoteAsyncTask extends AsyncTask<Student, Void, Void> {
//        private StudentDao studentDao;
//
//        private InsertNoteAsyncTask(StudentDao studentDao){
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Student... students) {
//            studentDao.insert(students[0]);
//            return null;
//        }
//    }
//
//
//    private static class UpdateNoteAsyncTask extends AsyncTask<Student, Void, Void> {
//        private StudentDao studentDao;
//
//        private UpdateNoteAsyncTask(StudentDao studentDao){
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Student... students) {
//            studentDao.update(students[0]);
//            return null;
//        }
//    }
//
//    private static class FindStudentTask extends AsyncTask<String, Void, Void> {
//        private StudentService studentService;
//
//        private FindStudentTask(StudentService studentService) { this.studentService = studentService }
//
//        @Override
//        protected Void doInBackground(String... username) {
//
//            return null;
//        }
//    }
}
