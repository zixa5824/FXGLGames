package com.almasb.fxglgames.spaceinvaders.level;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxglgames.spaceinvaders.Config.ENEMIES_PER_ROW;
import static com.almasb.fxglgames.spaceinvaders.Config.ENEMY_ROWS;
import static java.lang.Math.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Level2 extends SpaceLevel {

    @Override
    public void init() {
        double t = 0;

        for (int y = 0; y < ENEMY_ROWS; y++) {
            for (int x = 0; x < ENEMIES_PER_ROW; x++) {

                FXGL.getMasterTimer().runOnceAfter(() -> {

                    Entity enemy = spawnEnemy(50, 50);

                    enemy.addComponent(new MoveComponent());

                }, Duration.seconds(t));

                t += 0.25;
            }
        }
    }

    private class MoveComponent extends Component {

        private double t = 0;

        @Override
        public void onUpdate(double tpf) {
            entity.setPosition(curveFunction().add(FXGL.getAppWidth() / 2, FXGL.getAppHeight() / 2 - 100));

            t += tpf;
        }

        private Point2D curveFunction() {
            double x = cos(t) * cos(t) * cos(t);
            double y = sin(t) * sin(t) * sin(t);

            return new Point2D(x, -y).multiply(145);
        }
    }
}
