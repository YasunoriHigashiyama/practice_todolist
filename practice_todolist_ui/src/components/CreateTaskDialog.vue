<template>
  <v-row>
    <v-dialog
      v-model="this.dialog"
      transition="dialog-bottom-transition"
      persistent
      max-width="600px"
    >
      <v-card>
        <v-card-title class="text-h5"> Task </v-card-title>
        <v-card-subtitle> {{ copyTask.id }}</v-card-subtitle>
        <v-card-text>
          <v-container>
            <v-row>
              <p class="red--text mb-0" style="white-space: pre-wrap">
                {{ this.message }}
              </p>
              <v-col cols="12">
                <v-text-field
                  class="mt-0"
                  label="title"
                  v-model="copyTask.title"
                  required
                ></v-text-field>
              </v-col>

              <v-col cols="12" sm="6" md="4">
                <v-menu
                  ref="start"
                  v-model="start"
                  :close-on-content-click="false"
                  :return-value.sync="copyTask.start"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="copyTask.start"
                      label="startDate"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="copyTask.start" no-title scrollable>
                    <v-spacer></v-spacer>

                    <v-btn
                      text
                      color="primary"
                      @click="$refs.start.save(copyTask.start)"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>

              <v-col cols="12" sm="6" md="4">
                <v-menu
                  ref="limit"
                  v-model="limit"
                  :close-on-content-click="false"
                  :return-value.sync="copyTask.limit"
                  transition="scale-transition"
                  offset-y
                  min-width="auto"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      v-model="copyTask.limit"
                      label="limitDate"
                      prepend-icon="mdi-calendar"
                      readonly
                      v-bind="attrs"
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="copyTask.limit" no-title scrollable>
                    <v-spacer></v-spacer>
                    <v-btn
                      text
                      color="primary"
                      @click="$refs.limit.save(copyTask.limit)"
                    >
                      OK
                    </v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>

              <v-col cols="12" class="mt-0 pt-0">
                <v-textarea
                  v-model="copyTask.memo"
                  class="mt-0 pt-0"
                  label="memo"
                  rows="3"
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="dialog = false">
            Cancel
          </v-btn>
          <v-btn color="green darken-1" text @click="create()"> Create </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  props: {
    task: {
      type: Object,
      default: () => ({
        id: "",
        title: "",
        start: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10),
        limit: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
          .toISOString()
          .substr(0, 10),
        memo: "",
      }),
    },
  },
  data() {
    return {
      dialog: false,
      start: false,
      limit: false,
      copyTask: {},
    };
  },
  computed: {
    message() {
      let message = "";
      if (
        this.copyTask.title == null ||
        this.copyTask.title == undefined ||
        this.copyTask.title == ""
      ) {
        message = message + "タイトルは必須です。\n";
      }
      if (new Date(this.copyTask.limit) < new Date(this.copyTask.start)) {
        message = message + "期限日は開始日よりも未来で設定してください。";
      }
      return message;
    },
  },
  mounted() {
    // console.log(this.task);
    // this.copyTask = Object.assign({}, this.task);
    // if (this.copyTask.id.length <= 0) {
    //   this.getId();
    // }
  },
  methods: {
    getId() {
      const date = new Date();
      const Y = date.getFullYear();
      const M = ("00" + (date.getMonth() + 1)).slice(-2);
      const D = ("00" + date.getDate()).slice(-2);
      const h = ("00" + date.getHours()).slice(-2);
      const m = ("00" + date.getMinutes()).slice(-2);
      const s = ("00" + date.getSeconds()).slice(-2);
      this.copyTask["id"] = Y + M + D + h + m + s;
    },
    create() {
      if (0 < this.message.length) {
        return;
      }
      this.$emit("addTask", this.copyTask);
      this.dialog = false;
    },

    open() {
      this.copyTask = Object.assign({}, this.task);
      if (this.copyTask.id.length <= 0) {
        this.getId();
      }
      this.dialog = true;
    },
  },
};
</script>