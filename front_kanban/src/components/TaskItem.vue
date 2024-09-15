<template>
        <v-card class="ma-2 simpleCard"
                @dragstart="startDrag($event, task)"
                draggable="true"
                :style="`border-left: 5px ${task.color} solid`"
        >
            <v-card-title class="py-1 px-2">
                <v-spacer></v-spacer>
                <h4 :class="{softDeleted : task.deleted}">{{ task.title }}</h4>
                <v-spacer></v-spacer>
            </v-card-title>
            <v-card-text class="pa-0">
                {{ task.description }}
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                        icon
                        color="red"
                        x-small
                        outlined
                        @click="deleteTask(task)"
                >
                    <v-icon>{{ icons.mdiDeleteOutline }}</v-icon>
                </v-btn>
                <v-btn
                        icon
                        color="blue"
                        x-small
                        outlined
                        @click="showUpdateTask = true"
                >
                    <v-icon>{{ icons.mdiPencilOutline }}</v-icon>
                </v-btn>
                <v-spacer></v-spacer>
            </v-card-actions>
            <v-dialog
                    v-model="showUpdateTask"
                    max-width="500"
            >
                <UpdateTask
                        :task="JSON.stringify(task)"
                        @close="showUpdateTask = false"
                ></UpdateTask>
            </v-dialog>
        </v-card>
</template>

<script>
import UpdateTask from "@/components/UpdateTask.vue";
import {mapActions} from "vuex";
import {mdiDeleteOutline, mdiPencilOutline} from '@mdi/js'

export default {
    name: 'TaskItem',
    props: ['task'],
    components: {UpdateTask},
    data: () => ({
        showUpdateTask: false,
        icons: {
            mdiDeleteOutline, mdiPencilOutline
        }
    }),
    methods: {
        ...mapActions(['updateTaskAction', 'hardDelete']),
        deleteTask(task) {
            if (!task.deleted) {
                task.deleted = true
                this.updateTaskAction(task)
            } else {
                this.hardDelete(task)
            }
        },
        startDrag(event, task) {
            event.dataTransfer.dropEffect = 'move'
            event.dataTransfer.effectAllowed = 'move'
            event.dataTransfer.setData('task', JSON.stringify(task))
        },
    }
}
</script>


<style scoped>
.simpleCard:hover {
    background: #eaeaea;
}

.softDeleted {
    text-decoration: line-through;
}

</style>