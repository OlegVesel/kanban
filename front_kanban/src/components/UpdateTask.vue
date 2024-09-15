<template>
    <v-card>
        <v-card-title :style="`background: ${item.color}`">
            Обновить задачу
        </v-card-title>
        <v-card-text>
            <v-text-field
                    label="Название задачи"
                    v-model="item.title"
            ></v-text-field>
            <v-textarea
                    rows="2"
                    label="Описание задачи"
                    v-model="item.description"
            ></v-textarea>
            <v-select
                    :items="variants"
                    item-text="text"
                    item-value="status"
                    label="Статус"
                    v-model="item.status"
            ></v-select>

            <v-row>
                <v-col>
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
                                Назначить цвет
                            </v-btn>
                        </template>
                        <ColorPicker
                                :color="item.color"
                                @color="setColor"
                                @close="showColorPicker = false"
                        />
                    </v-menu>
                </v-col>
                <v-col align="end">
                    <v-chip
                            ref="some"
                            label
                    >
                        Создана: {{item.createDate}}
                    </v-chip>
                </v-col>
            </v-row>
            <v-switch
                    v-model="item.deleted"
                    label="Помечена на удаление"
            ></v-switch>
        </v-card-text>
        <v-card-actions>
            <v-btn
                    color="green"
                    @click="updateTask"
            >
                Обновить
            </v-btn>
            <v-btn
                    color="red"
                    @click="$emit('close')"
            >
                Отмена
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import {mapActions} from "vuex";
import ColorPicker from "@/tools/ColorPicker";

export default {
    name: 'UpdateTask',
    props: ['task'],
    data: () => ({
        showColorPicker: false,
        item: {
            id: null,
            title: null,
            description: null,
            createDate: null,
            deleted: null,
            status: null,
            color: null,
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
        ]
    }),
    components: {
        ColorPicker
    },
    watch: {
        task() {
            this.prepare()
        },
    },
    methods: {
        ...mapActions(['updateTaskAction']),
        prepare() {
            this.item = JSON.parse(this.task)
            this.oldStatus = this.item.status
        },
        updateTask() {
            this.updateTaskAction(this.item)
            this.$emit('close')
            for (let key in this.item) {
                this.item[key] = null
            }
        },
        setColor(color) {
            if (color != null && color.length > 0) {
                this.item.color = color
            }
            this.showColorPicker = false
        }
    },
    beforeMount() {
        this.prepare()
    }
}
</script>
<style scoped>

</style>