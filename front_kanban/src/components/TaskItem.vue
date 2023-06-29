<template>
        <v-card class="ma-2 simpleCard"
                @dragstart="startDrag($event, task)"
                draggable="true"
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

.ag-courses_item {
    flex-basis: calc(33.33333% - 30px);
    margin: 0 15px 30px;
    overflow: hidden;
    border-radius: 5px;
}

.ag-courses-item_link {
    display: block;
    padding: 15px 5px;
    background-color: #eaeaea;
    overflow: hidden;
    position: relative;
    text-decoration: none;
}

.ag-courses-item_link:hover,
.ag-courses-item_link:hover .ag-courses-item_date {
    text-decoration: none;
    color: #FFF;
}

.ag-courses-item_link:hover .ag-courses-item_bg {
    -ms-transform: scale(10);
    transform: scale(10);
}

.ag-courses-item_title {
    min-height: 25px;
    margin: 0 0 25px;
    overflow: hidden;
    font-weight: bold;
    font-size: 30px;
    color: #000;
    z-index: 2;
    position: relative;
}

.ag-courses-item_date-box {
    font-size: 18px;
    color: #FFF;
    z-index: 2;
    position: relative;
}

.ag-courses-item_date {
    font-weight: bold;
    color: #f9b234;
    -webkit-transition: color .5s ease;
    -o-transition: color .5s ease;
    transition: color .5s ease
}

.ag-courses-item_bg {
    height: 128px;
    width: 128px;
    background-color: #f9b234;
    z-index: 1;
    position: absolute;
    top: -75px;
    right: -75px;
    border-radius: 50%;
    -webkit-transition: all .5s ease;
    -o-transition: all .5s ease;
    transition: all .5s ease;
}

@media only screen and (max-width: 979px) {
    .ag-courses_item {
        flex-basis: calc(50% - 30px);
    }

    .ag-courses-item_title {
        font-size: 24px;
    }
}

@media only screen and (max-width: 639px) {
    .ag-courses_item {
        flex-basis: 100%;
    }

    .ag-courses-item_title {
        min-height: 72px;
        line-height: 1;

        font-size: 24px;
    }

    .ag-courses-item_link {
        padding: 22px 40px;
    }

    .ag-courses-item_date-box {
        font-size: 16px;
    }
}
</style>