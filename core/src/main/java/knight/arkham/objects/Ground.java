package knight.arkham.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;

import static knight.arkham.helpers.Box2DHelper.createBody;

public class Ground extends GameObject {

    private boolean setToDestroy;
    private boolean isDestroyed;

    public Ground(Rectangle bounds, World world) {
        super(bounds, world, new TextureRegion(new Texture("img/scenario/ground.png")));
    }

    private void destroyBody() {

        actualWorld.destroyBody(body);
        isDestroyed = true;

        stateTimer = 0;
    }

    @Override
    protected Body createObjectBody() {
        return createBody(new Box2DBody(actualBounds, actualWorld, null));
    }

    @Override
    protected void childUpdate(float deltaTime) {

        if (setToDestroy && !isDestroyed)
            destroyBody();
    }

    @Override
    public void draw(Batch batch) {
        if (!isDestroyed)
            super.draw(batch);
    }

    public void setToDestroy() {
        setToDestroy = true;
    }
}
