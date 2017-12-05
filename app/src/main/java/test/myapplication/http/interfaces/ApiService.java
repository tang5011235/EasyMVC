package test.myapplication.http.interfaces;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import test.myapplication.http.Bean.FuLiBean;
import test.myapplication.http.Bean.GankBaseResponse;

/**
 * Created by Administrator on 2017/12/4.
 */

public interface ApiService {
    @GET("data/Android/10/1")
    public Observable<GankBaseResponse<List<FuLiBean>>> getFuLi();
}
