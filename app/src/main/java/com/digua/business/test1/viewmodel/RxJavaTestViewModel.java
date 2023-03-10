package com.digua.business.test1.viewmodel;

import androidx.databinding.ObservableField;
import androidx.room.Room;

import com.digua.App;
import com.digua.core.net.BaseObserver;
import com.digua.core.net.RxUtil;
import com.digua.core.utils.LogUtils;
import com.digua.core.vm.BaseViewModel;
import com.digua.repository.db.AppDatabase;
import com.digua.repository.db.dao.UserDao;
import com.digua.repository.db.entity.UserEntity;
import com.digua.repository.net.company.Api;
import com.digua.repository.net.company.bean.LoginRequest;
import com.digua.repository.net.company.bean.LoginResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestViewModel extends BaseViewModel {


    private Observable dbObservable;
    private Observable netObservable;
    private Observable threadObservable;

    public ObservableField<String> msg = new ObservableField<>("");
    public ObservableField<Boolean> requestNum = new ObservableField<>(false);

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void initObservable() {
        getDBUserList();
        login();
        myThread();
    }

    /**
     * DB
     */
    public void getDBUserList() {

        dbObservable = Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserEntity>> emitter) throws Exception {
                try {
                    AppDatabase db = AppDatabase.getInstance(App.getInstance().getApplicationContext());
                    UserDao userDao = db.userDao();
                    List<UserEntity> userEntities = userDao.getAll();
                    emitter.onNext(userEntities);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e.fillInStackTrace());
                }
            }
        }).compose(RxUtil.io_main());
    }

    /**
     * NET
     */
    public void login() {
        netObservable = Api.getInstance()
                .getApiService()
                .login(new LoginRequest("xs", "xs"))
                .compose(RxUtil.io_main());
    }

    /**
     * Thread
     */
    public void myThread() {
        threadObservable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Thread.sleep(500);
                emitter.onNext("thread - 1");
                Thread.sleep(500);
                emitter.onNext("thread - 2");
                Thread.sleep(500);
                emitter.onNext("thread - End");
                emitter.onComplete();
            }
        });
    }


    /**
     * 合并-无序
     */
    public void merge() {

        ArrayList<Observable> observables = new ArrayList<>();
        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);

        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);

        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);

        Observable[] observables1 = observables.toArray(new Observable[observables.size()]);

        // Schedulers.io()是顺序执行的，如果要真正实现并发，必须要用Schedulers.newThread()
        Observable.mergeArray(observables1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {

                    @Override
                    protected void onStart() {
                        super.onStart();
                        baseProgressBar.postValue(true);
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        baseProgressBar.postValue(false);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        LogUtils.e2("【onComplete】");
                        baseProgressBar.postValue(false);
                        requestNum.set(true);
                    }


                    @Override
                    public void onNext(Object obj) {

                        if (obj instanceof List) {
                            List<UserEntity> userEntities = (List<UserEntity>) obj;
                            String msg1 = msg.get() + "【DB】" + "人数：" + userEntities.size();
                            msg.set(msg1 + "\n");
                        } else if (obj instanceof LoginResponse) {
                            LoginResponse goodsDetailBean = (LoginResponse) obj;
                            String msg1 = msg.get() + "【NET】" + goodsDetailBean.getData().getUserInfo().getRoleNames();
                            msg.set(msg1 + "\n");
                        } else if (obj instanceof String) {
                            String content = (String) obj;
                            String msg1 = msg.get() + "【Thread】" + content;
                            msg.set(msg1 + "\n");
                        }

                    }
                });
    }


    /**
     * 合并-有序
     */
    public void concat() {

        ArrayList<Observable> observables = new ArrayList<>();
        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);

        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);

        observables.add(dbObservable);
        observables.add(netObservable);
        observables.add(threadObservable);


        Observable[] observables1 = observables.toArray(new Observable[observables.size()]);

        // Schedulers.io()是顺序执行的，如果要真正实现并发，必须要用Schedulers.newThread()
        Observable.concatArray(observables1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    protected void onStart() {
                        super.onStart();
                        baseProgressBar.postValue(true);
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        baseProgressBar.postValue(false);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        LogUtils.e2("【Concat onComplete】");
                        baseProgressBar.postValue(false);
                        requestNum.set(true);
                    }


                    @Override
                    public void onNext(Object obj) {

                        if (obj instanceof List) {
                            List<UserEntity> userEntities = (List<UserEntity>) obj;
                            String msg1 = msg.get() + "【Concat DB】" + "人数：" + userEntities.size();
                            msg.set(msg1 + "\n");
                        } else if (obj instanceof LoginResponse) {
                            LoginResponse goodsDetailBean = (LoginResponse) obj;
                            String msg1 = msg.get() + "【Concat NET】" + goodsDetailBean.getData().getUserInfo().getRoleNames();
                            msg.set(msg1 + "\n");
                        } else if (obj instanceof String) {
                            String content = (String) obj;
                            String msg1 = msg.get() + "【Concat Thread】" + content;
                            msg.set(msg1 + "\n");
                        }


                    }
                });
    }


}
