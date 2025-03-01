/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2022 The ZAP Development Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zaproxy.addon.retire.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;

/** Tests for upstream {@code jsrepository.json} file. */
class UpstreamJsRepositoryUnitTest {

    @Test
    void shouldParseUpstreamJsRepository() {
        // Given
        String path = "/org/zaproxy/addon/retire/resources/jsrepository.json";
        Set<String> expectedSeverities = Vulnerability.SEVERITY_MAP.keySet();
        // When / Then
        Repo repo = assertDoesNotThrow(() -> new Repo(path));
        for (RepoEntry entry : repo.getEntries().values()) {
            for (Vulnerability vuln : entry.getVulnerabilities()) {
                assertThat(vuln.getSeverity().toLowerCase(Locale.ROOT), is(in(expectedSeverities)));
            }
        }
    }
}
