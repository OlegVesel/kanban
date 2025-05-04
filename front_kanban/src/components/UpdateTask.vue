<template>
    <div>
        <v-card
                style="background: #f0efeb">
            <v-card-title :style="`background: ${item.color}`">
                Обновить задачу
            </v-card-title>
            <v-card-text>
                <v-form ref="form_update_task">
                    <v-text-field
                            label="Название задачи"
                            v-model="item.title"
                            :rules="rules"
                    ></v-text-field>
                    <v-textarea
                            rows="2"
                            label="Описание задачи"
                            v-model="item.description"
                    ></v-textarea>
                    <v-row>
                        <v-col class="pb-0  my-0">
                            <v-select
                                    :items="variants"
                                    item-text="text"
                                    item-value="status"
                                    label="Статус"
                                    v-model="item.status"
                            ></v-select>
                        </v-col>
                        <v-col align="end" align-self="center" cols="4" class="pb-0  my-0">
                            <v-menu
                                    v-model="showColorPicker"
                                    :close-on-content-click="false"
                                    offset-x
                            >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                            color="primary"
                                            dark
                                            small
                                            v-bind="attrs"
                                            v-on="on"
                                            @click="showColorPicker = true"
                                    >
                                        Выбрать цвет
                                    </v-btn>
                                </template>
                                <ColorPicker
                                        :color="item.color"
                                        @color="setColor"
                                        @close="showColorPicker = false"
                                        @clear="clearColor"
                                />
                            </v-menu>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col align-self="center" class="pt-0 my-0">
                            <v-switch
                                    v-model="item.deleted"
                                    label="Помечена на удаление"
                            ></v-switch>
                        </v-col>
                        <v-col align="end" align-self="center" cols="5" class="pt-0  my-0">
                            <v-chip
                                    ref="some"
                                    label
                            >
                                Создана: {{ item.createDate }}
                            </v-chip>
                        </v-col>
                    </v-row>

                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-row class="ma-2">
                    <v-tooltip top>
                        <template v-slot:activator="{ on, attrs }">
                            <v-progress-circular
                                    @click="showSubTasks = !showSubTasks"
                                    :value="progress"
                                    class="mr-2"
                                    v-bind="attrs"
                                    v-on="on"
                            ></v-progress-circular>
                        </template>
                        <span>Подзадачи</span>
                    </v-tooltip>
                    <v-spacer/>

                    <v-btn
                            small
                            color="success"
                            @click="updateTask"
                            class="mx-2"
                    >
                        Обновить
                    </v-btn>
                    <v-btn
                            small
                            color="error"
                            @click="$emit('close')"
                            class="mx-2"
                    >
                        Отмена
                    </v-btn>
                </v-row>

            </v-card-actions>
            <v-expand-transition>
                <div v-show="showSubTasks">
                    <v-divider></v-divider>
                    <SubTaskList
                            :subs="item.subTasks"
                            @subTasks="addSubTasks"
                    ></SubTaskList>
                </div>
            </v-expand-transition>
        </v-card>
    </div>
</template>

<script>
import {mapActions} from "vuex";
import {mdiFormatListCheckbox} from '@mdi/js'
import ColorPicker from "@/tools/ColorPicker";
import SubTaskList from "@/components/SubTaskList";

export default {
    name: 'UpdateTask',
    props: ['task'],
    data: () => ({
        showColorPicker: false,
        showSubTasks: false,
        item: {
            id: null,
            title: null,
            description: null,
            createDate: null,
            deleted: null,
            status: null,
            color: null,
            subTasks: []
        },
        oldStatus: null,
        variants: [
            {
                text: 'Запланированная',
                status: 0
            },
            {
                text: 'В работе',
                status: 1
            },
            {
                text: 'Выполненная',
                status: 2
            },
        ],
        rules: [
            value => !!value || 'Не должно быть пустым!',
        ],
        icons: {
            mdiFormatListCheckbox
        },
    }),
    components: {
        ColorPicker, SubTaskList
    },
    watch: {
        task() {
            this.prepare()
        },
    },
    computed: {
        completedTasks() {
            return this.item.subTasks.filter(task => task.done).length
        },
        progress() {
            if (this.item.subTasks.length === 0)
                return 0
            return this.completedTasks / this.item.subTasks.length * 100
        },
    },
    methods: {
        ...mapActions(['updateTaskAction']),
        prepare() {
            this.item = JSON.parse(this.task)
            this.oldStatus = this.item.status
        },
        updateTask() {
            if (!this.$refs.form_update_task.validate())
                return
            this.updateTaskAction(this.item)
            this.$emit('close')
            // for (let key in this.item) {
            //     this.item[key] = null
            // }
        },
        setColor(color) {
            if (color !== null && color.length > 0) {
                this.item.color = color
            }
            this.showColorPicker = false
        },
        clearColor() {
            this.item.color = '#f0efeb'
            this.showColorPicker = false
        },
        addSubTasks(subs) {
            this.item.subTasks = subs
        }
    },
    beforeMount() {
        this.prepare()
    }
}
</script>
<style scoped>

</style>