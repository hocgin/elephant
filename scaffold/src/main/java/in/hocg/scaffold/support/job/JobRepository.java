package in.hocg.scaffold.support.job;

/**
 * @author hocgin
 * @date 18-10-10
 **/
public interface JobRepository {
    
    void submit();
    
    void remove();
    
    void resume();
    
    void pause();
    
    void update();
    
    void list();
}
