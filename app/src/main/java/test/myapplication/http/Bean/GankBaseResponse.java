package test.myapplication.http.Bean;

/**
 * Created by Administrator on 2017/11/29.
 */

public class GankBaseResponse<T> extends BaseResponse {
    private boolean erro;
    private T results;

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public boolean isOk() {
        return !erro;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
