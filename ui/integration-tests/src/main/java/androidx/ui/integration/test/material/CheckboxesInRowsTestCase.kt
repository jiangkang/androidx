/*
 * Copyright 2019 The Android Open Source Project
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

package androidx.ui.integration.test.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Text
import androidx.ui.integration.test.ToggleableTestCase
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.ui.test.ComposeTestCase

/**
 * Test case that puts the given amount of checkboxes into a column of rows and makes changes by
 * toggling the first checkbox.
 */
class CheckboxesInRowsTestCase(
    private val amountOfCheckboxes: Int
) : ComposeTestCase, ToggleableTestCase {

    private val states = mutableListOf<MutableState<Boolean>>()

    @Composable
    override fun emitContent() {
        MaterialTheme {
            Surface {
                Column {
                    repeat(amountOfCheckboxes) {
                        Row {
                            Text(text = "Check Me!")
                            CheckboxWithState(
                                Modifier.weight(1f).wrapContentSize(Alignment.CenterEnd)
                            )
                        }
                    }
                }
            }
        }
    }

    override fun toggleState() {
        val state = states.first()
        state.value = !state.value
    }

    @Composable
    fun CheckboxWithState(modifier: Modifier = Modifier) {
        val state = state { false }
        states.add(state)
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = !state.value },
            modifier = modifier
        )
    }
}
