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
          <ToolBar ref="option" @addTask="addTask" @option="emitOption" />
        </div>
        <v-divider></v-divider>

        <v-row class="mt-3">
          <div v-for="task in tasks" :key="task.title">
            <TaskCard
              :ref="task.id"
              :task="task"
              @edit="editTask"
              @delete="deleteTask(task.id)"
              @addTask="addTask"
              @updateStatus="updateStatus"
            />
          </div>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
/* eslint no-unused-vars: 0 */
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
      let params = this.getOption();
      this.$axios
        .get("/tasks", { params })
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.tasks = response.data.data;
          }
        })
        .catch((e) => {
          alert(e);
        });
    },

    addTask(task) {
      this.$axios
        .post("/create/Task", task)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    editTask(task) {
      this.$axios
        .post("/edit/Task", task)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    emitOption(option) {
      //optionが変更されたときに呼ばれる
      this.getTasks();
    },
    getOption() {
      //オプションを取得してくる
      return this.$refs.option.getOption();
    },
    deleteTask(ref) {
      var id = this.$refs[ref][0].task.id;
      this.$axios
        .post("/delete/task", id)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
    updateStatus(task) {
      let param = {};
      param["id"] = task.id;
      param["status"] = task.status;
      this.$axios
        .post("/update/status", param)
        .then((response) => {
          if (response.status == 200) {
            this.getTasks();
          }
        })
        .catch((e) => {
          alert(e);
        });
    },
  },
};
</script>
