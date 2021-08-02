<template>
  <v-container>
    <v-row>
      <v-col>
        <v-row class="mt-4 pb-1">
          <div class="text-h3 text--secondary" style="letter-spacing: 2px">
            ToDo List
          </div>
        </v-row>

        <div class="mt-4 pb-1">
          <ToolBar @addTask="addTask" />
        </div>
        <v-divider></v-divider>

        <v-row class="mt-3">
          <div v-for="task in tasks" :key="task.title">
            <TaskCard
              :ref="task.id"
              :task="task"
              @emit="emitTask"
              @delete="deleteTask(task.id)"
            />
          </div>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import ToolBar from "../components/ToolBar.vue";
import TaskCard from "../components/Task.vue";
export default {
  components: { ToolBar, TaskCard },
  data() {
    return {
      tasks: [],
      taskTemplate: {
        id: "",
        title: "",
        start: "",
        limit: "",
        memo: "",
        status: false,
      },
    };
  },
  computed: {},
  mounted() {
    this.getTasks();
  },
  methods: {
    getTasks() {
      this.$axios
        .get("/tasks")
        .then((response) => {
          this.tasks = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
    addTask(task) {
      this.$axios
        .post("/task", task)
        .then((response) => {
          this.tasks = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
    emitTask() {
      console.log("a");
    },
    deleteTask(ref) {
      var id = this.$refs[ref][0].task.id;
      this.$axios
        .post("/delete/task", id)
        .then((response) => {
          this.tasks = response.data;
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
};
</script>
