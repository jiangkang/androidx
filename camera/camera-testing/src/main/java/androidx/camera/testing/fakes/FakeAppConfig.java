/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.camera.testing.fakes;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraDeviceSurfaceManager;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ExtendableUseCaseConfigFactory;
import androidx.camera.core.LensFacing;
import androidx.camera.core.UseCaseConfigFactory;

/**
 * Convenience class for generating a fake {@link CameraXConfig}.
 *
 * <p>This {@link CameraXConfig} contains all fake CameraX implementation components.
 */
public final class FakeAppConfig {
    private FakeAppConfig() {
    }

    private static final String CAMERA_ID_0 = "0";
    private static final String CAMERA_ID_1 = "1";

    /** Generates a fake {@link CameraXConfig}. */
    @NonNull
    public static CameraXConfig create() {
        FakeCameraFactory cameraFactory = new FakeCameraFactory();
        cameraFactory.insertCamera(LensFacing.BACK, CAMERA_ID_0,
                () -> new FakeCamera(null, new FakeCameraInfoInternal(0, LensFacing.BACK)));
        cameraFactory.insertCamera(LensFacing.FRONT, CAMERA_ID_1,
                () -> new FakeCamera(null, new FakeCameraInfoInternal(0, LensFacing.FRONT)));

        CameraDeviceSurfaceManager surfaceManager = new FakeCameraDeviceSurfaceManager();
        UseCaseConfigFactory defaultConfigFactory = new ExtendableUseCaseConfigFactory();

        CameraXConfig.Builder appConfigBuilder =
                new CameraXConfig.Builder()
                        .setCameraFactory(cameraFactory)
                        .setDeviceSurfaceManager(surfaceManager)
                        .setUseCaseConfigFactory(defaultConfigFactory);

        return appConfigBuilder.build();
    }
}
