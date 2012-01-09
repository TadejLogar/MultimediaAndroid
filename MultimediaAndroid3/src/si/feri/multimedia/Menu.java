package si.feri.multimedia;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Menu implements ApplicationListener {
	private PerspectiveCamera camera;
	private Mesh[] faces;
	public int n;
	private SpriteBatch spb;
	private BitmapFont font;
	private Texture textureLogo;
	private float first;
	private float second;
	private float up;
	private float down;
	private float color1;
	private float color2;
	private float color3;
	private float color4;
	private float color5;
	private float black;
	private float green;
	private Mesh[] faces2;
	
	public Menu() {}

	public void create() {

		if (faces == null) {
			faces = new Mesh[25];

			for (int i = 0; i < 25; i++) {
				faces[i] = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3, "a_position"), new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

				faces[i].setIndices(new short[] { 0, 1, 2, 3 });
			}

			black = Color.toFloatBits(2, 22, 21, 255);
			green = Color.toFloatBits(0, 79, 0, 255);

			first = 3.5f;
			second = -3.5f;

			up = 2f;
			down = up - 1f;

			color1 = Color.toFloatBits(133, 176, 10, 255);
			color2 = Color.toFloatBits(200, 255, 200, 255);
			color3 = Color.toFloatBits(100, 232, 20, 255);
			color4 = Color.toFloatBits(206, 10, 86, 255);
			color5 = Color.toFloatBits(10, 176, 176, 255);

			for (int i = 0, j = 0; j < 4; j++, i += 2) {

				faces[0 + (j * 6)].setVertices(new float[] {
					first, up - i, 0.5f, green,
					second, up - i, 0.5f, green,
					first, down - i, 0.5f, green,
					second, down - i, 0.5f, green
				});

				
				
				  faces[1+(j * 6)].setVertices(new float[] { //zadaj
			         first, up - i, -0.5f, color1,
			         second, up - i, -0.5f, color1,
			         first, down - i, -0.5f, color1,
			         second, down - i, -0.5f, color1
		         });
		        
		         faces[2+(j * 6)].setVertices(new float[] { // zgoraj
		         first, up - i, -0.5f, color2,
		         second, up - i, -0.5f, color2,
		         first, up - i, 0.5f, color2,
		         second, up - i, 0.5f, color2 });
		        
		         faces[3+(j * 6)].setVertices(new float[] { // spodaj
		         first, down - i, -0.5f, color3,
		         second, down - i, -0.5f, color3,
		         first, down - i, 0.5f, color3,
		         second, down - i, 0.5f, color3 });
		        
		         faces[4+(j * 6)].setVertices(new float[] { // desna
		         first, up - i, 0.5f, color4,
		         first, down - i, 0.5f, color4,
		         first, up - i, -0.5f, color4,
		         first, down - i, -0.5f, color4 });
		        
		         faces[5+(j * 6)].setVertices(new float[] { // leva
		         second, up - i, 0.5f, color5,
		         second, down - i, 0.5f, color5,
		         second, up - i, -0.5f, color5,
		         second, down - i, -0.5f, color5 });
				 
			}

			float backgound = Color.toFloatBits(24, 86, 143, 255);
			float backgound2 = Color.toFloatBits(41, 152, 234, 255);

			faces[24].setVertices(new float[] { // ozadje - sivo
			15, 20, -30, backgound, -15, 20, -10, backgound, 15, -20, -10, backgound2, -15, -20, -10, backgound2 });

		}

		faces2 = new Mesh[2];

		for (int i = 0; i < faces2.length; i++) {
			faces2[i] = new Mesh(true, 4, 4, new VertexAttribute(Usage.Position, 3, "a_position"), new VertexAttribute(Usage.ColorPacked, 4, "a_color"));

			faces2[i].setIndices(new short[] { 0, 1, 2, 3 });

		}

		faces2[0].setVertices(new float[] { 15, 20, -10, color5, -15, 20, -10, color5, 15, -20, -10, color5, -15, -20, -10, color5 });

		faces2[1].setVertices(new float[] { first, up - 5, 0.5f, color4, second, up - 5, 0.5f, color4, first, down - 5, 0.5f, color4, second, down - 5, 0.5f, color4 });

		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);

		spb = new SpriteBatch();
		font = new BitmapFont();
		font.setScale(1.5f);
		font.setColor(255, 255, 255, 255);

		font2 = new BitmapFont();
		font2.setScale(2f);
		font2.setColor(100, 100, 100, 255);

		textureLogo = new Texture(Gdx.files.internal("data/logo.png"));

	}

	// @Override
	public void dispose() {

	}

	// @Override
	public void pause() {
	}

	int lastTouchX;
	int lastTouchY;
	private int lastI;
	private boolean touchOut;
	private boolean author;

	private BitmapFont font2;
	private boolean touchOut2;

	// @Override
	public void render() {

		if (n == 0) {
			camera.position.x = 3f;
			camera.position.y = 0f;
			camera.position.z = 10;
		}

		camera.lookAt(0, 0, 0);

		GL10 gl = Gdx.gl10;
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_DEPTH_TEST);

		camera.update();
		camera.apply(Gdx.gl10);

		if (author) {
			for (Mesh face : faces2) {
				face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
			}
		} else {
			for (Mesh face : faces) {
				face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
			}
		}

		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
		}

		n++;

		gl.glDisable(GL10.GL_DEPTH_TEST);

		float height = Gdx.graphics.getHeight(); // višina
		float width = Gdx.graphics.getWidth(); // širina

		spb.begin();

		spb.draw(textureLogo, 20, height - textureLogo.getHeight() - 20);

		if (Gdx.input.isTouched()) {
			lastTouchX = Gdx.input.getX();
			lastTouchY = Gdx.input.getY();

			System.out.println(lastTouchX + "x" + lastTouchY);

			float[] temp = new float[] { first, up - 0, 0.5f, color1, second, up - 0, 0.5f, color1, first, down - 0, 0.5f, color1, second, down - 0, 0.5f, color1 };

			for (int i = 0; i < 4; i++) {
				faces[i*6].getVertices(temp);
				temp[3] = green;
				temp[7] = green;
				temp[11] = green;
				temp[15] = green;
				faces[i*6].setVertices(temp);
			}

			int i = -1;
			if (lastTouchY >= 180 && lastTouchY <= 220) {
				i = 0;
			} else if (lastTouchY >= 250 && lastTouchY <= 310) {
				i = 1;
			} else if (lastTouchY >= 350 && lastTouchY <= 400) {
				i = 2;
				if (!author && touchOut2) {
					author = true;
				}
			} else if (lastTouchY >= 430 && lastTouchY <= 480) {
				i = 3;
				if (lastI == i && touchOut) {
					System.exit(0);
				}
			}

			if (lastTouchY >= 390 && lastTouchY <= 440) {
				author = false;
			}

			lastI = i;
			touchOut = false;
			touchOut2 = false;

			if (i != -1) {
				faces[i*6].getVertices(temp);
				temp[3] = color5;
				temp[7] = color5;
				temp[11] = color5;
				temp[15] = color5;
				faces[i*6].setVertices(temp);
			}
		} else {
			touchOut = true;
			touchOut2 = true;
		}

		if (author) {
			font2.draw(spb, "Tadej Logar", 25, 400);
			font2.draw(spb, "FERI", 25, 350);
			font.draw(spb, "Raèunalniška vecpredtavnost", 25, 300);

			font.draw(spb, "Nazaj", 125, 140);
		} else {
			font.draw(spb, "Prehrana", 105, 350);
			font.draw(spb, "Igra", 125, 270);
			font.draw(spb, "Avtor", 125, 180);
			font.draw(spb, "Izhod", 125, 105);
		}

		spb.end();

	}

	// @Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
		camera.near = 0.1f;

	}

	// @Override
	public void resume() {
	}

}