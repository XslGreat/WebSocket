package com.xl.bitcion;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Random;

/**
 * <br />
 * 创建于2018.03.19
 *
 * @author 谢山林
 * @version 1.0
 */

@WebServlet(name="BitCoinDataCenter",urlPatterns = "/BitCoinDataCenter",loadOnStartup=1)
public class BitCoinDataCenter extends HttpServlet implements Runnable{

    public void init(ServletConfig config) {
        startUp();
    }

    public void startUp() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        int bitPrice = 10000;
        while(true) {
            //每隔1-3秒就产生一个新价格
            int duration = 1000 + new Random().nextInt(2000);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //新的价格围绕10000的50%波动
            float random = 1 + (float) (Math.random() - 0.5);
            int newPrice = (int) (bitPrice * random);

            //查看的人越多,价格就越高
            int total = ServerManager.getTotal();
            newPrice = total * newPrice;

            String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
            String message = String.format(messageFormat,newPrice,total);

            //广播出去
            ServerManager.broadCast(message);
        }
    }
}
