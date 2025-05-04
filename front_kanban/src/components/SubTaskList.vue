<template>
    <v-card class="subtaskCard">
        <v-card-text>
            <v-text-field
                    v-model="newTask"
                    label="Добавить подзадачи"
                    @keydown.enter="create"
            >
                <template v-slot:append>
                    <v-fade-transition>
                        <v-icon
                                v-if="newTask"
                                @click="create"
                                color="success"
                        >
                            {{ icons.mdiPlusCircle }}
                        </v-icon>
                    </v-fade-transition>
                </template>
            </v-text-field>

            <v-card v-if="tasks.length > 0">
                <v-slide-y-transition
                        class="py-0"
                        group
                >
                    <template v-for="(task, i) in tasks">
                        <v-divider
                                v-if="i !== 0"
                                :key="`${i}-divider`"
                        ></v-divider>

                        <v-list-item :key="`${i}-${task.text}`" style="background-color: #f0efeb">
                            <v-list-item-action>
                                <v-checkbox
                                        :on-icon="icons.mdiCheckboxMarkedCircle"
                                        :off-icon="icons.mdiCheckboxBlankCircleOutline"
                                        v-model="task.done"
                                        color="success"
                                >
                                    <template v-slot:label>
                                        <div
                                                :class="task.done && 'grey--text' || 'primary--text'"
                                                class="ml-4"
                                                v-text="task.title"
                                        ></div>
                                    </template>
                                </v-checkbox>
                            </v-list-item-action>
                            <v-spacer></v-spacer>
                            <v-btn
                                icon
                                x-small
                                color="error"
                                @click="tasks.splice(i, 1)"
                            >
                                <v-icon>{{ icons.mdiClose }}</v-icon>
                            </v-btn>
                        </v-list-item>
                    </template>
                </v-slide-y-transition>
            </v-card>
        </v-card-text>
    </v-card>
</template>

<script>
import {mdiPlusCircle, mdiCheckboxMarkedCircle, mdiCheckboxBlankCircleOutline, mdiCheck, mdiClose} from '@mdi/js'

export default {
    name: "SubTaskList",
    props:['subs'],
    data: () => ({
        tasks: [

        ],
        icons: {mdiPlusCircle, mdiCheckboxMarkedCircle, mdiCheckboxBlankCircleOutline, mdiCheck,  mdiClose},
        newTask: null,
    }),
    watch:{
        tasks(newVal){
            this.$emit('subTasks', newVal)
        }
    },
    methods: {
        create() {
            this.tasks.push({
                done: false,
                title: this.newTask,
            })
            this.newTask = null
        },
    },
    beforeMount() {
        this.tasks = this.subs
    }
}
</script>

<style scoped>
.subtaskCard{
    background-color: #f0efeb;
}
</style>