<template>
    <v-card>
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
            <v-btn
                color="green"
                @click="saveTask"
            >
                Создать
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
import {mapActions, mapGetters} from "vuex";

export default {
    name:'AddTaskDialog',
    data:() => ({
        item : {
            title: '',
            description: '',
            idUser : ''
        }
    }),
    computed: {
        ...mapGetters(['getAccount'])
    },
    methods:{
        ...mapActions(['addTask']),
        saveTask(){
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