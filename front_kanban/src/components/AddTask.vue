<template>
    <v-card style="background: #f0efeb">
        <v-card-title>
            Добавить задачу
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
        </v-card-text>
        <v-card-actions>
            <v-row class="ma-0" justify="center">
                <v-btn
                        small
                        color="success"
                        @click="saveTask"
                        class="mx-2"
                >
                    Создать
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
    </v-card>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
    name: 'AddTaskDialog',
    data: () => ({
        item: {
            title: '',
            description: '',
            idUser: ''
        }
    }),
    computed: {
        ...mapGetters(['getAccount'])
    },
    methods: {
        ...mapActions(['addTask']),
        saveTask() {
            this.item.idUser = this.getAccount.id
            this.addTask(this.item)
            this.item.title = ''
            this.item.description = ''
            this.item.idUser = ''
            this.$emit('close')
        }
    }
}
</script>

<style scoped>

</style>