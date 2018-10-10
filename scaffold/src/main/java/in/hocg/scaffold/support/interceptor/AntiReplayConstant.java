package in.hocg.scaffold.support.interceptor;

/**
 * @author hocgin
 * @date 18-8-20
 * 防重放攻击配置
 **/
public interface AntiReplayConstant {
    String ANTI_REPLAY_PARAMETER_SIGN = "sign";
    String ANTI_REPLAY_PARAMETER_TIMESTAMP = "timestamp";
    String ANTI_REPLAY_PARAMETER_NONCE = "nonce";
    int ANTI_REPLAY_INTERVAL = 60 * 1000;
}
