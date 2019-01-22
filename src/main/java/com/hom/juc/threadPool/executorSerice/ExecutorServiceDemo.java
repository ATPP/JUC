package com.hom.juc.threadPool.executorSerice;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService提高页面渲染速度，为每一张图的下载创建一个独立任务，从CompletionService获取结果
 */
public class ExecutorServiceDemo {

    private final ExecutorService executorService;

    ExecutorServiceDemo(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(CharSequence source) throws ExecutionException {
        List<ImageInfo> infos = scanForInageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executorService);
        for (final ImageInfo imageInfo : infos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
            renderText(source);
            try {
                for (int t = 0, n = infos.size(); t < n; t++) {
                    Future<ImageData> f = completionService.take();
                    ImageData imageData = f.get();
                    RenderImage(imageData);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new ExecutionException(e.getCause());
            }
        }
    }

    private void RenderImage(ImageData imageData) {
    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForInageInfo(CharSequence source) {
        return null;
    }

    public class ImageInfo {
        public ImageData downloadImage() {
            return null;
        }
    }

}
