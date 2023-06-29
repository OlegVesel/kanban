<template>
    <v-card>
        <v-card-title>
            Обновить задачу
        </v-card-title>
        <v-card-text>
            <v-text-field
                    label="Название задачи"
                    v-model="item.title"
            ></v-text-field>
            <v-text-field
                    label="Описание задачи"
                    v-model="item.description"
            ></v-text-field>
            <v-select
                    :items="variants"
                    item-text="text"
                    item-value="status"
                    label="Статус"
                    v-model="item.status"
            ></v-select>
            <v-switch
                    v-model="item.deleted"
                    label="Помечена на удаление"
            ></v-switch>

            <v-text-field ref="some"
                          readonly
                          label="дата создания задачи"
                          v-model="item.createDate"
            >
            </v-text-field>
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

export default {
    name: 'UpdateTask',
    props: ['task'],
    data: () => ({
        item: {
            id: null,
            title: null,
            description: null,
            createDate: null,
            deleted: null,
            status: null,
        },
        oldStatus : null,
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
        }
    },
    beforeMount() {
        this.prepare()
    }
}
</script>
<style scoped>

</style>