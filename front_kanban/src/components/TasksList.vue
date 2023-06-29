<template>
    <v-container ref="tasksList">
        <v-row>
            <v-col>
                <h3>
                    Мои задачи
                </h3>
            </v-col>
        </v-row>
        <v-card
                v-scroll.self="onScroll"
                class="overflow-y-auto"
                max-height="800"
                min-height="50"
                elevation="0"
                @drop="onDropInProject($event)"
                @dragenter.prevent
                @dragover.prevent
                id="taskList"
        >
            <TaskItem
                    v-for="item in getProjectTasks"
                    :key="item.id"
                    :task="item"
            >
            </TaskItem>
        </v-card>

        <v-row justify="center" class="my-3">
            <v-btn
                    icon
                    color="green"
                    small
                    outlined
                    @click="showAddTask = true"
            >
                <v-icon>
                    {{ icons.mdiPlus }}
                </v-icon>
            </v-btn>
        </v-row>
        <v-dialog
                v-model="showAddTask"
                max-width="500"
        >
            <AddTask
                    @success="uploadTasks"
                    @close="showAddTask = false"
            ></AddTask>
        </v-dialog>
    </v-container>
</template>

<script>
import TaskItem from "@/components/TaskItem.vue";
import AddTask from "@/components/AddTask.vue";
import {mapActions, mapGetters} from "vuex";
import {mdiPlus} from '@mdi/js'

export default {
    name: 'TasksList',
    components: {AddTask, TaskItem},
    data: () => ({
        showAddTask: false,
        icons: {
            mdiPlus
        },
        scrollInvoked: 0,
    }),
    computed: {
        ...mapGetters(['getProjectTasks'])
    },
    methods: {
        ...mapActions(['updateTaskAction']),
        uploadTasks() {
            this.showAddTask = false
        },
        onScroll() {
            this.scrollInvoked++
        },
        onDropInProject(event){
            let result = event.dataTransfer.getData('task')
            let task = JSON.parse(result)
            task.status = 0
            this.updateTaskAction(task)
        }
    },
    beforeMount() {
    }
}
</script>


<style scoped>

</style>