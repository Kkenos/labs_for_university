package com.kkenos.deliverypizza;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private List<Node> nodes;
    private Map<Long, Node> nodeMap;
    private List<Node> path;

    private double minX, maxX, minY, maxY;
    private float scale, offsetX, offsetY;
    private Node startNode, endNode;

    private SpriteBatch batch;
    private BitmapFont font;
    private String currentAlgorithmName = "Algorithm A*";

    private int clicksQtyty = 0; // (0 first click), (1 second click)
    private int selectedAlgorithm = 0; // (0 A*), (1 Dijkstra)

    // Переменные для анимации "бегущего" пути
    private float pathAnimationProgress = 0f;
    private final float ANIMATION_SPEED = 25f; // Скорость бега линии (сколько сегментов в секунду)

    // Кастомная цветовая палитра
    private final Color colorBackground = new Color(0.08f, 0.09f, 0.12f, 1f); // Глубокий темный
    private final Color colorEdges = new Color(0.22f, 0.26f, 0.32f, 1f);      // Стальной сине-серый
    private final Color colorNodes = new Color(0.45f, 0.5f, 0.58f, 1f);       // Прохладный серый
    private final Color colorStart = new Color(1f, 0.55f, 0f, 1f);            // Неоновый оранжевый
    private final Color colorEnd = new Color(1f, 0.2f, 0.3f, 1f);             // Малиново-красный
    private final Color colorPath = new Color(0f, 0.95f, 1f, 1f);             // Светящийся бирюзовый

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(3.0f);

        GraphLoader loader = new GraphLoader();
        try {
            loader.loadNodes("assets/omsk/nodes.csv");
            loader.LoadEdges("assets/omsk/edges.csv");
        } catch (Exception e) {
            System.out.println("Ошибка загрузки файлов!");
            e.printStackTrace();
            Gdx.app.error("FILE ERROR", "Ошибка загрузки файлов");
            return;
        }

        nodeMap = loader.getNodeMap();
        nodes = new ArrayList<>(nodeMap.values());

        if (nodes.isEmpty()) {
            System.out.println("Граф пуст! Проверьте пути к CSV файлам.");
            return;
        }

        minX = nodes.get(0).x;
        maxX = nodes.get(0).x;
        minY = nodes.get(0).y;
        maxY = nodes.get(0).y;

        for (Node n : nodes) {
            if (n.x < minX) minX = n.x;
            else if (n.x > maxX) maxX = n.x;

            if (n.y < minY) minY = n.y;
            else if (n.y > maxY) maxY = n.y;
        }

        scale = 6500;
        offsetX = 150;
        offsetY = 40;

        Gdx.input.setInputProcessor(new InputAdapter() {
            boolean drag = false;
            int startScreenX;
            int startScreenY;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button != Input.Buttons.LEFT) {
                    drag = true;
                    startScreenX = screenX;
                    startScreenY = screenY;
                    return true;
                }
                Vector3 worldCoords = new Vector3(screenX, screenY, 0);
                camera.unproject(worldCoords);

                long selectedNodeId = -1;
                boolean hit = false;
                float maxClickRadiusInPixels = 30f;
                float minDistanceSq = maxClickRadiusInPixels * maxClickRadiusInPixels;

                for (Node n : nodes) {
                    float nodeX = (float) ((n.x - minX) * scale) + offsetX;
                    float nodeY = (float) ((n.y - minY) * scale) + offsetY;
                    float dx = worldCoords.x - nodeX;
                    float dy = worldCoords.y - nodeY;
                    float distSq = dx * dx + dy * dy;

                    if (distSq < minDistanceSq) {
                        minDistanceSq = distSq;
                        selectedNodeId = n.id;
                        hit = true;
                    }
                }

                if (clicksQtyty == 0) {
                    if (hit) {
                        clicksQtyty = 1;
                        startNode = nodeMap.get(selectedNodeId);
                        endNode = null; // Обнуляем старый финиш при выборе нового старта
                        path = null;    // Сбрасываем старый путь
                        System.out.println("Выбрана начальная точка А: " + startNode.id);
                    }
                } else {
                    if (hit) {
                        clicksQtyty = 0;
                        endNode = nodeMap.get(selectedNodeId);
                        System.out.println("Выбрана конечная точка Б: " + endNode.id);

                        for (Node n : nodes) {
                            n.minDistance = Double.MAX_VALUE;
                            n.parent = null;
                        }

                        Dijkstra algorithm;
                        if (selectedAlgorithm == 0) {
                            System.out.println("Запуск алгоритма A*");
                            algorithm = new AStar();
                        } else {
                            System.out.println("Запуск алгоритма Дейкстры");
                            algorithm = new Dijkstra();
                        }

                        long startTime = System.currentTimeMillis();
                        algorithm.findPath(startNode, endNode, nodeMap);
                        long endTime = System.currentTimeMillis();

                        path = algorithm.getPath(endNode);
                        pathAnimationProgress = 0f; // СБРОС АНИМАЦИИ: путь начинает бежать заново

                        if (path.isEmpty()) {
                            System.out.println("Путь не найден.");
                        } else {
                            System.out.println("Прошло " + (endTime - startTime) + " мс");
                            System.out.println("Кратчайшее расстояние = " + endNode.minDistance);
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.B) {
                    if (selectedAlgorithm == 0) {
                        selectedAlgorithm = 1;
                        currentAlgorithmName = "Algorithm Dijkstra";
                        System.out.println("Выбран Алгоритм Дейкстры");
                    } else {
                        selectedAlgorithm = 0;
                        currentAlgorithmName = "Algorithm A*";
                        System.out.println("Выбран Алгоритм A*");
                    }
                    // Если путь уже был построен, пересчитаем его новым алгоритмом
                    if (startNode != null && endNode != null && clicksQtyty == 0) {
                        for (Node n : nodes) {
                            n.minDistance = Double.MAX_VALUE;
                            n.parent = null;
                        }
                        Dijkstra algorithm = (selectedAlgorithm == 0) ? new AStar() : new Dijkstra();
                        algorithm.findPath(startNode, endNode, nodeMap);
                        path = algorithm.getPath(endNode);
                        pathAnimationProgress = 0f;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (drag) {
                    offsetX -= (startScreenX - screenX);
                    offsetY += (startScreenY - screenY);
                    startScreenX = screenX;
                    startScreenY = screenY; // Плавное и точное перемещение без ограничений сложности
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                drag = false;
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                Vector3 mouseCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(mouseCoords);
                float mouseX = mouseCoords.x;
                float mouseY = mouseCoords.y;

                float oldScale = scale;
                float zoomFactor = 1.1f;

                if (amountY < 0) scale *= zoomFactor;
                else scale /= zoomFactor;

                float scaleRatio = scale / oldScale;
                offsetX = mouseX - (mouseX - offsetX) * scaleRatio;
                offsetY = mouseY - (mouseY - offsetY) * scaleRatio;

                return true;
            }
        });
    }

    @Override
    public void render() {
        // 1. Обновление логики анимации бега пути
        if (path != null && path.size() > 1) {
            pathAnimationProgress += Gdx.graphics.getDeltaTime() * ANIMATION_SPEED;
            if (pathAnimationProgress > path.size() - 1) {
                pathAnimationProgress = path.size() - 1; // Путь добежал до конца
            }
        }

        // Очистка экрана новым фоновым цветом
        ScreenUtils.clear(colorBackground);
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // 2. Отрисовка всех рёбер графа (Связи между узлами)
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(colorEdges);
        for (Node u : nodes) {
            for (Edge e : u.neighbors) {
                Node v = nodeMap.get(e.idEnd);
                if (v == null) continue;

                float ux = (float) ((u.x - minX) * scale) + offsetX;
                float uy = (float) ((u.y - minY) * scale) + offsetY;
                float vx = (float) ((v.x - minX) * scale) + offsetX;
                float vy = (float) ((v.y - minY) * scale) + offsetY;
                shapeRenderer.line(ux, uy, vx, vy);
            }
        }
        shapeRenderer.end();

        // 3. Отрисовка самих узлов (Точки графа)
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(colorNodes);
        for (Node n : nodes) {
            float x = (float) ((n.x - minX) * scale) + offsetX;
            float y = (float) ((n.y - minY) * scale) + offsetY;
            shapeRenderer.circle(x, y, 2);
        }

        // Подсветка выбранной точки А (Старт)
        if (startNode != null) {
            shapeRenderer.setColor(colorStart);
            float x = (float) ((startNode.x - minX) * scale) + offsetX;
            float y = (float) ((startNode.y - minY) * scale) + offsetY;
            shapeRenderer.circle(x, y, 7);
        }

        // Подсветка выбранной точки Б (Финиш)
        if (endNode != null) {
            shapeRenderer.setColor(colorEnd);
            float x = (float) ((endNode.x - minX) * scale) + offsetX;
            float y = (float) ((endNode.y - minY) * scale) + offsetY;
            shapeRenderer.circle(x, y, 7);
        }
        shapeRenderer.end();

        // 4. Отрисовка АНИМИРОВАННОГО пути ("Бежит" от А до Б)
        if (path != null && path.size() > 1) {
            Gdx.gl.glLineWidth(5); // Делаем путь толще и заметнее
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(colorPath);

            int fullSegments = (int) pathAnimationProgress;

            // Отрисовываем полностью пройденные участки пути
            for (int i = 0; i < fullSegments; i++) {
                Node u = path.get(i);
                Node v = path.get(i + 1);

                float ux = (float) ((u.x - minX) * scale) + offsetX;
                float uy = (float) ((u.y - minY) * scale) + offsetY;
                float vx = (float) ((v.x - minX) * scale) + offsetX;
                float vy = (float) ((v.y - minY) * scale) + offsetY;
                shapeRenderer.line(ux, uy, vx, vy);
            }

            // Отрисовываем неполный (текущий бегущий) сегмент методом интерполяции
            if (fullSegments < path.size() - 1) {
                float t = pathAnimationProgress - fullSegments; // Текущая доля от 0.0 до 1.0
                Node u = path.get(fullSegments);
                Node v = path.get(fullSegments + 1);

                float ux = (float) ((u.x - minX) * scale) + offsetX;
                float uy = (float) ((u.y - minY) * scale) + offsetY;
                float vx = (float) ((v.x - minX) * scale) + offsetX;
                float vy = (float) ((v.y - minY) * scale) + offsetY;

                // Вычисляем промежуточную точку, куда линия "добежала" прямо сейчас
                float partialVx = ux + (vx - ux) * t;
                float partialVy = uy + (vy - uy) * t;

                shapeRenderer.line(ux, uy, partialVx, partialVy);
            }

            shapeRenderer.end();
            Gdx.gl.glLineWidth(1); // Возвращаем стандартную толщину линий
        }

        // 5. Отрисовка интерфейсного текста
        batch.begin();
        font.draw(batch, currentAlgorithmName, 20, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
