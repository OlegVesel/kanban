<template>
    <v-card class="ma-2 simpleCard"
            @dragstart="startDrag($event, task)"
            draggable="true"
            elevation="3"
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
            <v-row class="ma-0" justify="center">
                <v-btn
                        icon
                        color="error"
                        x-small
                        outlined
                        @click="deleteTask(task)"
                        class="mx-1"
                >
                    <v-icon>{{ icons.mdiDeleteOutline }}</v-icon>
                </v-btn>
                <v-btn
                        icon
                        color="primary"
                        x-small
                        outlined
                        @click="showUpdateTask = true"
                        class="mx-1"
                >
                    <v-icon>{{ icons.mdiPencilOutline }}</v-icon>
                </v-btn>
            </v-row>
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
    background: #d9d9d9;
    border: #033047 solid 1px !important;
    transition: all .5s ease;
    cursor: pointer;
}

.simpleCard {
    border: #f0efeb solid 1px;
    background: #f0efeb;
}

.softDeleted {
    text-decoration: line-through;
}

</style>